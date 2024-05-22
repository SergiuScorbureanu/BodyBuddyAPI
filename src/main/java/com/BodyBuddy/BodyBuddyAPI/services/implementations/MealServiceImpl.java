package com.BodyBuddy.BodyBuddyAPI.services.implementations;

import com.BodyBuddy.BodyBuddyAPI.models.Meal;
import com.BodyBuddy.BodyBuddyAPI.models.User;
import com.BodyBuddy.BodyBuddyAPI.models.dto.MealDTO;
import com.BodyBuddy.BodyBuddyAPI.repositories.MealRepository;
import com.BodyBuddy.BodyBuddyAPI.repositories.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public List<Meal> getMeals() {
        return mealRepository.findAll();
    }

    @Override
    public List<Meal> getMealsByUserId(UUID userId) {
        return mealRepository.findByUser_Id(userId);
    }

    @Override
    public List<Meal> getMealsByUserIdAndDate(UUID userId, String date) {
        return mealRepository.findByUserIdAndDate(userId, date);
    }

    @Override
    public void createMealFromDTO(MealDTO mealDTO) {
        User user = userRepository.findById(UUID.fromString(mealDTO.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + mealDTO.getUserId()));

        Meal meal = new Meal();
        //mealDTO.getFoods().stream().map(food -> new MealFoods(meal.getId(), food.getId())).collect(Collectors.toCollection());
        meal.setUser(user);
        meal.setMealType(mealDTO.getMealType());
        meal.setCalories(mealDTO.getCalories());
        meal.setCarbohydrates(mealDTO.getCarbohydrates());
        meal.setFat(mealDTO.getFat());
        meal.setProtein(mealDTO.getProtein());
        meal.setDate(mealDTO.getDate());

        mealRepository.save(meal);
        mealDTO.getFoods().forEach(mealFood -> meal.addFood(mealFood.getFood(), mealFood.getQuantity()));
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
