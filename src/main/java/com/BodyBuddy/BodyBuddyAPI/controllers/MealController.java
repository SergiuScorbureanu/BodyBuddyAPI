package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.models.Meal;
import com.BodyBuddy.BodyBuddyAPI.models.dto.MealDTO;
import com.BodyBuddy.BodyBuddyAPI.models.dto.OldFoodDTO;
import com.BodyBuddy.BodyBuddyAPI.models.dto.OldMealDTO;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.MealServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/bodybuddy/v1/meals")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class MealController {

    private final MealServiceImpl mealService;

    @GetMapping(path = "/{userId}/{date}")
    public ResponseEntity<List<OldMealDTO>> getMealsByUserIdAndDate(
            @PathVariable UUID userId,
            @PathVariable String date) {
        try {
            List<Meal> meals = mealService.getMealsByUserIdAndDate(userId, date);
            if (meals.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<OldMealDTO> oldMealDTOS = meals.stream().map(meal -> {
                OldMealDTO oldMealDTO = new OldMealDTO();
                oldMealDTO.setMealType(meal.getMealType());
                oldMealDTO.setProtein(meal.getProtein());
                oldMealDTO.setFat(meal.getFat());
                oldMealDTO.setCarbohydrates(meal.getCarbohydrates());
                oldMealDTO.setCalories(meal.getCalories());
                oldMealDTO.setUserId(meal.getUser().getId().toString());
                oldMealDTO.setFoods(meal.getFoods().stream()
//                        .map(MealFood::getFood)
                        .map(food -> {
                    OldFoodDTO oldFoodDTO = new OldFoodDTO();
                    oldFoodDTO.setName(food.getFood().getName());
                    oldFoodDTO.setQuantity(food.getFood().getQuantity());
                    oldFoodDTO.setProtein(food.getFood().getProtein());
                    oldFoodDTO.setMeasurementUnit(food.getFood().getMeasurementUnit());
                    oldFoodDTO.setCarbohydrates(food.getFood().getCarbohydrates());
                    oldFoodDTO.setCalories(food.getFood().getCalories());
                    oldFoodDTO.setFats(food.getFood().getFat());
                    oldFoodDTO.setMealQuantity(food.getQuantity());
                    return oldFoodDTO;
                }).collect(Collectors.toList()));
                return oldMealDTO;
            }).toList();
            return ResponseEntity.ok(oldMealDTOS);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createMeal(@RequestBody MealDTO mealDTO) {
        try {
            mealService.createMealFromDTO(mealDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "The meal has been added successfully!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateMeal(@PathVariable UUID id, @RequestBody Meal updatedMeal) {
        try {
            mealService.updateMeal(id, updatedMeal);
            return ResponseEntity.ok("The meal has been updated successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteMeal(@PathVariable UUID id) {
        try {
            mealService.deleteMeal(id);
            return ResponseEntity.ok("The meal has been deleted successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
