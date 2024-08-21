package com.BodyBuddy.BodyBuddyAPI.config;

import com.BodyBuddy.BodyBuddyAPI.models.dto.googleDtos.UserInfoDto;
import com.BodyBuddy.BodyBuddyAPI.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenIntrospectionService {

    private final WebClient userInfoClient;
    private final JwtUtils jwtUtils;

    public OAuth2AuthenticatedPrincipal introspectToken(String token) {
        if (jwtUtils.validateJwtToken(token)) {
            String username = jwtUtils.getUserNameFromJwtToken(token);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("username", username);
            return new OAuth2IntrospectionAuthenticatedPrincipal(username, attributes, null);
        }
        try {
            UserInfoDto userInfoDto = userInfoClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/oauth2/v3/userinfo")
                            .queryParam("access_token", token)
                            .build())
                    .retrieve()
                    .bodyToMono(UserInfoDto.class)
                    .block();
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("sub", userInfoDto.sub());
            attributes.put("name", userInfoDto.name());
            attributes.put("email", userInfoDto.email());
            attributes.put("picture", userInfoDto.picture());
            return new OAuth2IntrospectionAuthenticatedPrincipal(userInfoDto.name(), attributes, null);
        } catch (Exception e) {
            System.err.println("Exception during token introspection: " + e.getMessage());
            return null;
        }
    }
}
