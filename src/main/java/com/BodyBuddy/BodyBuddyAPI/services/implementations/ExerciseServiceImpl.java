package com.BodyBuddy.BodyBuddyAPI.services.implementations;

import com.BodyBuddy.BodyBuddyAPI.models.Exercise;
import com.BodyBuddy.BodyBuddyAPI.repositories.ExerciseRepository;
import com.BodyBuddy.BodyBuddyAPI.services.interfaces.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Override
    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public void createExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @Override
    public void updateExercise(UUID id, Exercise updatedExercise) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);

        if (optionalExercise.isPresent()) {
            Exercise exercise = optionalExercise.get();
            exercise.setName(updatedExercise.getName());
            exercise.setSetNumber(updatedExercise.getSetNumber());
            exercise.setRepNumber(updatedExercise.getRepNumber());
            exercise.setPauseTime(updatedExercise.getPauseTime());
            exercise.setImage(updatedExercise.getImage());
            exerciseRepository.save(exercise);

        } else {
            throw new NoSuchElementException("The exercise with the id " + " doesn't exist in the database.");
        }
    }

    @Override
    public void deleteExercise(UUID id) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);

        if (optionalExercise.isPresent()) {
            Exercise exercise = optionalExercise.get();
            exerciseRepository.delete(exercise);
        } else {
            throw new NoSuchElementException("The exercise with the id " + " doesn't exist in the database.");
        }
    }
}
