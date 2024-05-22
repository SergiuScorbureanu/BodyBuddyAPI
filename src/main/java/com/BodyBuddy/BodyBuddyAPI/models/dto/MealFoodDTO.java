package com.BodyBuddy.BodyBuddyAPI.models.dto;

import com.BodyBuddy.BodyBuddyAPI.models.Food;
import com.BodyBuddy.BodyBuddyAPI.models.MealFood;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MealFoodDTO {

    private Food food;
    private int quantity;

}
