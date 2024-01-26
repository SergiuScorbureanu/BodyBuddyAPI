package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.models.Training;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.TrainingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/bodybuddy/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;


    @GetMapping
    public List<Training> getTrainings() {
        return trainingService.getTrainings();
    }


    @PostMapping
    public void createTrainign(@RequestBody Training training) {
        trainingService.createTraining(training);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateTraining(@PathVariable UUID id, @RequestBody Training updatedTraining) {
        try {
            trainingService.updateTraining(id, updatedTraining);
            return ResponseEntity.ok("The training has been updated successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteTraining(@PathVariable UUID id) {
        try {
            trainingService.deleteTraining(id);
            return ResponseEntity.ok("The training has been deleted successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

