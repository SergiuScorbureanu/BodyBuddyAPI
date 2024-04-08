package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.models.Food;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.FoodServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/bodybuddy/v1/foods")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FoodController {

    private final FoodServiceImpl foodService;

    @GetMapping
    public ResponseEntity<List<Food>> getFoods() {
        List<Food> foods = foodService.getFoods();
        if (foods.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(foods);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<Food> getFoodByName(@PathVariable String name) {
        try {
            Food food = foodService.getFoodByName(name);
            return ResponseEntity.ok(food);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createFood(@RequestBody Food food) {
        foodService.createFood(food);
        return ResponseEntity.ok("The food has been added successfully!");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateFood(@PathVariable UUID id, @RequestBody Food updatedFood) {
        try {
            foodService.updateFood(id, updatedFood);
            return ResponseEntity.ok("The food has been updated successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable UUID id) {
        try {
            foodService.deleteFood(id);
            return ResponseEntity.ok("The food has been deleted successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
