package com.BodyBuddy.BodyBuddyAPI.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String type;

    public static Optional<Gender> getGenderByFieldString(String field) {
        return Arrays.stream(Gender.values())
                .filter(genderElement -> genderElement.type.equals(field))
                .findAny();
    }
}
