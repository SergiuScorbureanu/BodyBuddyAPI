package com.BodyBuddy.BodyBuddyAPI.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoodDTO {
    private UUID id;
    private String name;
    private Double calories;
    private Double carbohydrates ;
    private Double fat;
    private Double protein;
    private String measurementUnit;
    private Double quantity;
}
