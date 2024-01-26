package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.models.Exercise;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.ExerciseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/bodybuddy/v1/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseServiceImpl exerciseService;

    @GetMapping
    public List<Exercise> getExercises() {
        return exerciseService.getExercises();
    }

    @PostMapping
    public void createExercise(@RequestBody Exercise exercise) {
        exerciseService.createExercise(exercise);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateExercise(@PathVariable UUID id, @RequestBody Exercise updatedExercise) {
        try {
            exerciseService.updateExercise(id, updatedExercise);
            return ResponseEntity.ok("The exercise has been updated successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable UUID id) {
        try {
            exerciseService.deleteExercise(id);
            return ResponseEntity.ok("The exercise has been deleted successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
