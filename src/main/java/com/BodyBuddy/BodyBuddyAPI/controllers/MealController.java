package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.models.Meal;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.MealServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/bodybuddy/v1/meals")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class MealController {

    private final MealServiceImpl mealService;

    @GetMapping
    public ResponseEntity<List<Meal>> getMeals() {
        List<Meal> meals = mealService.getMeals();
        if (meals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(meals);
    }


    @PostMapping
    public ResponseEntity<String> createMeal(@RequestBody Meal meal) {
        mealService.createMeal(meal);
        return ResponseEntity.ok("The meal has been added successfully!");
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
