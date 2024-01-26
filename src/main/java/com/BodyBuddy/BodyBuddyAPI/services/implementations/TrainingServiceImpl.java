package com.BodyBuddy.BodyBuddyAPI.services.implementations;

import com.BodyBuddy.BodyBuddyAPI.models.Training;
import com.BodyBuddy.BodyBuddyAPI.repositories.TrainingRepository;
import com.BodyBuddy.BodyBuddyAPI.services.interfaces.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    @Override
    public List<Training> getTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public void createTraining(Training training) {
        trainingRepository.save(training);
    }

    @Override
    public void updateTraining(UUID id, Training updatedTraining) {
        Optional<Training> optionalTraining = trainingRepository.findById(id);

        if (optionalTraining.isPresent()) {
            Training training = optionalTraining.get();
            training.setExercises(updatedTraining.getExercises());
            training.setTitle(updatedTraining.getTitle());
            training.setDuration(updatedTraining.getDuration());
            training.setContent(updatedTraining.getContent());
            training.setBackgroundImg(updatedTraining.getBackgroundImg());
            training.setColor(updatedTraining.getColor());
            trainingRepository.save(training);

        } else {
            throw new NoSuchElementException("The training with the id " + " doesn't exist in the database.");
        }
    }

    @Override
    public void deleteTraining(UUID id) {
        Optional<Training> optionalTraining = trainingRepository.findById(id);

        if (optionalTraining.isPresent()) {
            Training training = optionalTraining.get();
            trainingRepository.delete(training);
        } else {
            throw new NoSuchElementException("The training with the id " + " doesn't exist in the database.");
        }
    }
}
