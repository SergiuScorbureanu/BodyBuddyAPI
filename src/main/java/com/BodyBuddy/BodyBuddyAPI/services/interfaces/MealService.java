package com.BodyBuddy.BodyBuddyAPI.services.interfaces;

import com.BodyBuddy.BodyBuddyAPI.models.Meal;

import java.util.List;
import java.util.UUID;

public interface MealService {

    public List<Meal> getMeals();

    //public List<Meal> getMealsByType(String mealType);

    public void createMeal(Meal meal);

    public void updateMeal(UUID id, Meal updatedMeal);

    public void deleteMeal(UUID id);
}
