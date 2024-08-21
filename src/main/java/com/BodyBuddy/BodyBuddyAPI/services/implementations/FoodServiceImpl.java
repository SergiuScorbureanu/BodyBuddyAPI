package com.BodyBuddy.BodyBuddyAPI.services.implementations;

import com.BodyBuddy.BodyBuddyAPI.models.Food;
import com.BodyBuddy.BodyBuddyAPI.repositories.FoodRepository;
import com.BodyBuddy.BodyBuddyAPI.services.interfaces.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public List<Food> getFoods() {
        return foodRepository.findAll();
    }

    @Override
    public Food getFoodByName(String name) {
        return foodRepository.findByName(name).orElseThrow(() ->
                new NoSuchElementException("Food with the id " + name + " doesn't exist in the database."));
    }

    @Override
    public void createFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void updateFood(UUID id, Food updatedFood) {
        Food food = foodRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Food with the id " + id + " doesn't exist in the database."));

        food.setName(updatedFood.getName());
        food.setCalories(updatedFood.getCalories());
        food.setCarbohydrates(updatedFood.getCarbohydrates());
        food.setFat(updatedFood.getFat());
        food.setProtein(updatedFood.getProtein());
        food.setMeasurementUnit(updatedFood.getMeasurementUnit());
        food.setQuantity(updatedFood.getQuantity());

        foodRepository.save(food);
    }

    @Override
    public void deleteFood(UUID id) {
        Optional<Food> optionalFood = foodRepository.findById(id);

        if(optionalFood.isPresent()) {
            Food food = optionalFood.get();
            foodRepository.delete(food);
        } else {
            throw new NoSuchElementException("Food with the id " + id + " doesn't exist in the database.");
        }

    }

    @Override
    public List<Food> searchFoods(String searchTerm) {
        return foodRepository.findByNameContainingIgnoreCase(searchTerm);
    }

}
