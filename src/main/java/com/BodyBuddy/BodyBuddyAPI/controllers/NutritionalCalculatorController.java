package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.models.dto.NutritionalValue;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.NutritionalCalculatorServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/bodybuddy/v1/nutritional-calculator")
//@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class NutritionalCalculatorController {

    private final NutritionalCalculatorServiceImpl nutritionalCalculatorService;
    private static final Logger logger = LoggerFactory.getLogger(NutritionalCalculatorController.class);


    @GetMapping(path = "/{id}")
    public ResponseEntity<NutritionalValue> getInitialMaxNutritionalValues(@PathVariable UUID id) {
        try {
            NutritionalValue nutritionalValue = new NutritionalValue();
            nutritionalValue.setCalories(nutritionalCalculatorService.calculateCalories(id));
            nutritionalValue.setProteins(nutritionalCalculatorService.calculateProtein(id));
            nutritionalValue.setFats(nutritionalCalculatorService.calculateFat(id));
            nutritionalValue.setCarbs(nutritionalCalculatorService.calculateCarbohydrate(id));
            logger.info("Calories calculated successfully for user ID: {}", id);
            return ResponseEntity.ok(nutritionalValue);
        } catch (EntityNotFoundException e) {
            logger.error("User not found for ID: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping(path = "/protein/{id}")
//    public ResponseEntity<Double> calculateProtein(@PathVariable UUID id) {
//        try {
//            double proteinRequirement = nutritionalCalculatorService.calculateProtein(id);
//            return ResponseEntity.ok(proteinRequirement);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping(path = "/fat/{id}")
//    public ResponseEntity<Double> calculateFat(@PathVariable UUID id) {
//        try {
//            double fatRequirement = nutritionalCalculatorService.calculateFat(id);
//            return ResponseEntity.ok(fatRequirement);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping(path = "/carbs/{id}")
//    public ResponseEntity<Double> calculateCarbohydrate(@PathVariable UUID id) {
//        try {
//            double carbohydrateRequirement = nutritionalCalculatorService.calculateCarbohydrate(id);
//            return ResponseEntity.ok(carbohydrateRequirement);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
