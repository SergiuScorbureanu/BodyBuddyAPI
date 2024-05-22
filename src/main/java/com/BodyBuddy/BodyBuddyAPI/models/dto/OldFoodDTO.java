package com.BodyBuddy.BodyBuddyAPI.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OldFoodDTO {

    private String name;
    private Double calories;
    private Double carbohydrates;
    private Double protein;
    private Double fats;
    private String measurementUnit;
    private Double quantity;
    private int mealQuantity;
}
