package com.BodyBuddy.BodyBuddyAPI.services.interfaces;

import com.BodyBuddy.BodyBuddyAPI.models.Exercise;

import java.util.List;
import java.util.UUID;

public interface ExerciseService {

    public List<Exercise> getExercises();

    public void createExercise(Exercise exercise);

    public void updateExercise(UUID id, Exercise updatedExercise);

    public void deleteExercise(UUID id);

}
