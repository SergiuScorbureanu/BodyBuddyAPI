package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.config.TokenIntrospectionService;
import com.BodyBuddy.BodyBuddyAPI.models.Role;
import com.BodyBuddy.BodyBuddyAPI.models.User;
import com.BodyBuddy.BodyBuddyAPI.models.dto.googleDtos.TokenDto;
import com.BodyBuddy.BodyBuddyAPI.models.dto.googleDtos.UrlDto;
import com.BodyBuddy.BodyBuddyAPI.models.enums.ERole;
import com.BodyBuddy.BodyBuddyAPI.payload.JwtResponse;
import com.BodyBuddy.BodyBuddyAPI.payload.LoginRequest;
import com.BodyBuddy.BodyBuddyAPI.payload.MessageResponse;
import com.BodyBuddy.BodyBuddyAPI.payload.SignupRequest;
import com.BodyBuddy.BodyBuddyAPI.repositories.RoleRepository;
import com.BodyBuddy.BodyBuddyAPI.repositories.UserRepository;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.UserDetailsImpl;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.UserServiceImpl;
import com.BodyBuddy.BodyBuddyAPI.util.JwtUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

@RestController
@RequestMapping(path = "/bodybuddy/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;
    private final TokenIntrospectionService tokenIntrospectionService;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientId}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque-token.clientSecret}")
    private String clientSecret;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse("ROLE_USER");

        return ResponseEntity
                .ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), role));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        System.out.println("Intra in signup");
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String strRole = signUpRequest.getRole();
        System.out.println("Role: " + strRole);
        Role role;

        if (strRole == null) {
            role = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else {
            switch (strRole) {
                case "admin":
                    role = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    break;
                case "mod":
                    role = roleRepository.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    break;
                default:
                    role = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            }
        }

        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logout() {
        System.out.println("logout succes");
        return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
    }

    // Google Auth
    @GetMapping("/url")
    public ResponseEntity<UrlDto> auth() {
        String url = new GoogleAuthorizationCodeRequestUrl(clientId,
                "http://localhost:4200",
                Arrays.asList(
                        "email",
                        "profile",
                        "openid")).build();

        return ResponseEntity.ok(new UrlDto(url));
    }

    @GetMapping("/callback")
    public ResponseEntity<TokenDto> callback(
            @RequestParam("code") String code,
            @RequestParam(value = "role", required = false) String role) throws URISyntaxException {

        if (role == null || role.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        String token;
        try {
            token = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(), new GsonFactory(),
                    clientId,
                    clientSecret,
                    code,
                    "http://localhost:4200"
            ).execute().getAccessToken();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            OAuth2AuthenticatedPrincipal principal = tokenIntrospectionService.introspectToken(token);
            if (principal != null) {
                String name = principal.getAttribute("name");
                String email = principal.getAttribute("email");
                String picture = principal.getAttribute("picture");
                ERole selectedRole = ERole.valueOf(role);

                User user = userService.saveOrUpdateUser(name, email, picture, selectedRole);

                String userId = user.getId().toString();
                String username = user.getUsername();
                String userEmail = user.getEmail();
                String userPicture = user.getPicture();
                String userRole = user.getRole().getName().name();
                return ResponseEntity.ok(new TokenDto(token, userId, username, userEmail, userPicture, userRole));
            } else {
                System.err.println("Invalid ID token.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            System.err.println("Exception token verification: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestBody String token) {
//        tokenBlacklistService.blacklistToken(token);
//        return ResponseEntity.ok(new HashMap<String, String>() {{
//            put("message", "Logged out successfully");
//        }});
//    }
}
