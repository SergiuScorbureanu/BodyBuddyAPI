package com.BodyBuddy.BodyBuddyAPI.models.dto.googleDtos;

public record TokenDto(
        String token,
        String id,
        String username,
        String email,
        String picture,
        String role
) {}
