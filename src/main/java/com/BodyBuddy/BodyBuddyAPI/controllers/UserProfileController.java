package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.models.User;
import com.BodyBuddy.BodyBuddyAPI.models.dto.googleDtos.UserDetailsDto;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bodybuddy/v1/user-details")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<UserDetailsDto> privateMessages(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body(new UserDetailsDto("Please Login", "", ""));
        }

        String email = principal.getAttribute("email");
        User user = userService.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.status(404).body(new UserDetailsDto("User not found", "", ""));
        }

        return ResponseEntity.ok(new UserDetailsDto(user.getUsername(), user.getEmail(), user.getPicture()));
    }
}
