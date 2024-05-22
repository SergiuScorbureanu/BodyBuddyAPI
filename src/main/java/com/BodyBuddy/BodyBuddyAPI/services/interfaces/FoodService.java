package com.BodyBuddy.BodyBuddyAPI.services.interfaces;

import com.BodyBuddy.BodyBuddyAPI.models.Food;
import com.BodyBuddy.BodyBuddyAPI.models.Meal;

import java.util.List;
import java.util.UUID;

public interface FoodService {

    public List<Food> getFoods();

    public Food getFoodByName(String name);

    public void createFood(Food food);

    public void updateFood(Long id, Food updatedFood);

    public void deleteFood(Long id);

    public List<Food> searchFoods(String searchTerm);
}
