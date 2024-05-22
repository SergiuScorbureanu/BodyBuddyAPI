package com.BodyBuddy.BodyBuddyAPI.services.interfaces;

import com.BodyBuddy.BodyBuddyAPI.models.Meal;
import com.BodyBuddy.BodyBuddyAPI.models.dto.MealDTO;

import java.util.List;
import java.util.UUID;

public interface MealService {

    public List<Meal> getMeals();

    public List<Meal> getMealsByUserId(UUID userId);

    public List<Meal> getMealsByUserIdAndDate(UUID userId, String date);

    //public List<Meal> getMealsByType(String mealType);

    public void createMealFromDTO(MealDTO mealDTO);

    public void updateMeal(UUID id, Meal updatedMeal);

    public void deleteMeal(UUID id);
}
