package com.BodyBuddy.BodyBuddyAPI.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NutritionalValue {
    private Integer calories;
    private Double carbs;
    private Double fats;
    private Double proteins;
}
