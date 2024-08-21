package com.BodyBuddy.BodyBuddyAPI.models.dto;

import com.BodyBuddy.BodyBuddyAPI.models.enums.EMealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OldMealDTO {

    private UUID id;
    private List<OldFoodDTO> foods;
    private String userId;
    private String date;
    private EMealType mealType;
    private Double calories;
    private Double carbohydrates;
    private Double fat;
    private Double protein;
}
