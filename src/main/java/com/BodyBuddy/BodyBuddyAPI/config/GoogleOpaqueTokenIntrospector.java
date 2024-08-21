package com.BodyBuddy.BodyBuddyAPI.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

@RequiredArgsConstructor
public class GoogleOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private final TokenIntrospectionService tokenIntrospectionService;

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        return tokenIntrospectionService.introspectToken(token);
    }
}
