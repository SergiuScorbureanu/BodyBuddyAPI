package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.models.Food;
import com.BodyBuddy.BodyBuddyAPI.models.dto.FoodDTO;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.FoodServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import java.util.*;

@RestController
@RequestMapping(path = "/bodybuddy/v1/foods")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class FoodController {

    private final FoodServiceImpl foodService;

    @GetMapping
    public ResponseEntity<List<FoodDTO>> getFoods() {
        List<Food> foods = foodService.getFoods();
        if (foods.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<FoodDTO> foodDTOS = foods.stream().map(food -> {
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setId(food.getId());
            foodDTO.setProtein(food.getProtein());
            foodDTO.setName(food.getName());
            foodDTO.setCarbohydrates(food.getCarbohydrates());
            foodDTO.setCalories(food.getCalories());
            foodDTO.setFat(food.getFat());
            foodDTO.setQuantity(food.getQuantity());
            foodDTO.setMeasurementUnit(food.getMeasurementUnit());
            return foodDTO;
        }).toList();
        return ResponseEntity.ok(foodDTOS);
    }

//    @GetMapping(path = "/{name}")
//    public ResponseEntity<Food> getFoodByName(@PathVariable String name) {
//        try {
//            Food food = foodService.getFoodByName(name);
//            return ResponseEntity.ok(food);
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFoods(@RequestParam String searchTerm) {
        List<Food> foods = foodService.searchFoods(searchTerm);
        if (foods.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(foods);
    }


    @PostMapping
    public ResponseEntity<Map<String, String>> createFood(@RequestBody Food food) {
        Map<String, String> response = new HashMap<>();
        try {
            foodService.createFood(food);
            response.put("message", "The food has been added successfully!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> updateFood(@PathVariable UUID id, @RequestBody Food updatedFood) {
        Map<String, String> response = new HashMap<>();
        try {
            foodService.updateFood(id, updatedFood);
            response.put("message", "The food has been updated successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            response.put("message", "Food not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> deleteFood(@PathVariable UUID id) {
        Map<String, String> response = new HashMap<>();
        try {
            foodService.deleteFood(id);
            response.put("message", "The food has been deleted successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            response.put("message", "Food not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
