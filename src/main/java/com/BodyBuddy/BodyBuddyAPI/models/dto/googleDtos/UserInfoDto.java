package com.BodyBuddy.BodyBuddyAPI.models.dto.googleDtos;

public record UserInfoDto(
        String sub,
        String name,
        String email,
        String picture
) {}
