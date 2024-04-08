package com.BodyBuddy.BodyBuddyAPI.services.implementations;

import com.BodyBuddy.BodyBuddyAPI.models.Meal;
import com.BodyBuddy.BodyBuddyAPI.repositories.MealRepository;
import com.BodyBuddy.BodyBuddyAPI.services.interfaces.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    @Override
    public List<Meal> getMeals() {
        return mealRepository.findAll();
    }

    @Override
    public void createMeal(Meal meal) {
        mealRepository.save(meal);
    }

    @Override
    public void updateMeal(UUID id, Meal updatedMeal) {
        Optional<Meal> optionalMeal = mealRepository.findById(id);

        if (optionalMeal.isPresent()) {
            Meal meal = optionalMeal.get();
            meal.setCalories(updatedMeal.getCalories());
            meal.setCarbohydrates(updatedMeal.getCarbohydrates());
            meal.setFat(updatedMeal.getFat());
            meal.setProtein(updatedMeal.getProtein());
            meal.setMealType(updatedMeal.getMealType());
            mealRepository.save(meal);
        } else {
            throw new NoSuchElementException("The meal with the id " + id + " doesn't exist in the database.");
        }
    }

    @Override
    public void deleteMeal(UUID id) {
        Optional<Meal> optionalMeal = mealRepository.findById(id);

        if (optionalMeal.isPresent()) {
            mealRepository.delete(optionalMeal.get());
        } else {
            throw new NoSuchElementException("The meal with the id " + id + " doesn't exist in the database.");
        }
    }

}
