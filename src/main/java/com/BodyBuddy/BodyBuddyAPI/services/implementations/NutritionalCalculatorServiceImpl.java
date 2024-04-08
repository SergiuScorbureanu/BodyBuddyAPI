package com.BodyBuddy.BodyBuddyAPI.services.implementations;

import com.BodyBuddy.BodyBuddyAPI.models.User;
import com.BodyBuddy.BodyBuddyAPI.models.UserParam;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EGender;
import com.BodyBuddy.BodyBuddyAPI.models.enums.ETrainingType;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EWeightGoal;
import com.BodyBuddy.BodyBuddyAPI.repositories.UserRepository;
import com.BodyBuddy.BodyBuddyAPI.services.interfaces.NutritionalCalculatorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NutritionalCalculatorServiceImpl implements NutritionalCalculatorService {

    // private final UserParamRepository userParamRepository;
    private final UserRepository userRepository;

//    @Override
//    public int calculateCalories(UUID id) {
//        UserParam userParam = userParamRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("UserParam not found"));
//
//        int age = calculateAge(userParam.getBirthDay(), LocalDate.now());
//        double bmr = calculateBMR(userParam.getWeight(), userParam.getHeight(), age, userParam.getGender());
//        double adjustedBmr = adjustBMRBasedOnActivity(bmr, userParam.getTrainingType());
//
//        double goalAdjustedCalories = adjustCaloriesBasedOnWeightGoal(adjustedBmr, userParam.getWeightGoal());
//
//        return (int) Math.round(goalAdjustedCalories);
//    }

    @Override
    public double adjustCaloriesBasedOnWeightGoal(double calories, EWeightGoal weightGoal) {
        return switch (weightGoal) {
            case LOSE_WEIGHT -> calories - 500;
            case GAIN_WEIGHT -> calories + 500;
            case MAINTAIN_WEIGHT -> calories;
        };
    }

    @Override
    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    @Override
    public double calculateBMR(double weight, double height, int age, EGender gender) {
        if (gender == EGender.MALE) {
            return 66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age);
        } else {
            return 655.1 + (9.563 * weight) + (1.850 * height) - (4.676 * age);
        }
    }

    @Override
    public double adjustBMRBasedOnActivity(double bmr, ETrainingType trainingType) {
        switch (trainingType) {
            case SEDENTARY:
                return bmr * 1.2;
            case LIGHTLY_ACTIVE:
                return bmr * 1.375;
            case MODERATELY_ACTIVE:
                return bmr * 1.55;
            case VERY_ACTIVE:
                return bmr * 1.725;
            case EXTRA_ACTIVE:
                return bmr * 1.9;
            default:
                throw new IllegalArgumentException("Invalid training type");
        }
    }

    // Helper methods to encapsulate common logic
    @Override
    public UserParam getUserParamForUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.getUserParams().stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("UserParam not found for given User"));
    }

    @Override
    public int calculateCalories(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Set<UserParam> userParams = user.getUserParams();

        UserParam userParam = userParams.stream().findFirst()
                .orElseThrow(() -> new EntityNotFoundException("UserParam not found for given User"));

        int age = calculateAge(userParam.getBirthDay(), LocalDate.now());
        double bmr = calculateBMR(userParam.getWeight(), userParam.getHeight(), age, userParam.getGender());
        double adjustedBmr = adjustBMRBasedOnActivity(bmr, userParam.getTrainingType());
        double goalAdjustedCalories = adjustCaloriesBasedOnWeightGoal(adjustedBmr, userParam.getWeightGoal());

        return (int) Math.round(goalAdjustedCalories);
    }

    @Override
    public double getProteinRequirementPerKg(ETrainingType trainingType) {
        return switch (trainingType) {
            case SEDENTARY -> 1.2;
            case LIGHTLY_ACTIVE -> 1.4;
            case MODERATELY_ACTIVE, VERY_ACTIVE -> 1.6;
            case EXTRA_ACTIVE -> 2.0;
            default -> throw new IllegalArgumentException("Invalid training type");
        };
    }

    @Override
    public double calculateProtein(UUID userId) {
        UserParam userParam = getUserParamForUser(userId);
        double proteinRequirementPerKg = getProteinRequirementPerKg(userParam.getTrainingType());
        return (int) Math.round(proteinRequirementPerKg * userParam.getWeight());
    }


    @Override
    public double calculateFat(UUID userId) {
        UserParam userParam = getUserParamForUser(userId);
        double bmr = calculateBMR(userParam.getWeight(), userParam.getHeight(), calculateAge(userParam.getBirthDay(), LocalDate.now()), userParam.getGender());
        double adjustedCalories = adjustBMRBasedOnActivity(bmr, userParam.getTrainingType());
        double caloriesFromFat = adjustedCalories * 0.3; // Presupunem 30% din calorii din grăsimi
        return (int) Math.round(caloriesFromFat / 9); // Convertim în grame, unde 1g de grăsime = 9 calorii
    }

    @Override
    public double calculateCarbohydrate(UUID userId) {
        UserParam userParam = getUserParamForUser(userId);
        double bmr = calculateBMR(userParam.getWeight(), userParam.getHeight(), calculateAge(userParam.getBirthDay(), LocalDate.now()), userParam.getGender());
        double adjustedCalories = adjustBMRBasedOnActivity(bmr, userParam.getTrainingType());
        double caloriesFromCarbs = adjustedCalories * 0.55; // Presupunem 55% din calorii din carbohidrați
        return (int) Math.round(caloriesFromCarbs / 4) ; // Convertim în grame, unde 1g de carbohidrați = 4 calorii
    }

//    @Override
//    public double calculateProtein(UUID id) {
//        UserParam userParam = userParamRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("UserParam not found"));
//
//        double proteinRequirementPerKg = switch (userParam.getTrainingType()) {
//            case SEDENTARY -> 1.2;
//            case LIGHTLY_ACTIVE -> 1.4;
//            case MODERATELY_ACTIVE, VERY_ACTIVE -> 1.6;
//            case EXTRA_ACTIVE -> 2.0;
//            default -> 1.2;
//        };
//
//        return proteinRequirementPerKg * userParam.getWeight();
//    }
//
//    @Override
//    public double calculateFat(UUID id) {
//        UserParam userParam = userParamRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("UserParam not found"));
//
//        int age = calculateAge(userParam.getBirthDay(), LocalDate.now());
//        double bmr = calculateBMR(userParam.getWeight(), userParam.getHeight(), age, userParam.getGender());
//        double adjustedCalories = adjustBMRBasedOnActivity(bmr, userParam.getTrainingType());
//
//        // Presupunem un procent de 30% din caloriile totale pentru necesarul de grăsimi
//        double caloriesFromFat = adjustedCalories * 0.3;
//
//        // Convertim caloriile din grăsimi în grame, știind că 1 gram de grăsime = 9 calorii
//        return caloriesFromFat / 9;
//    }
//
//    @Override
//    public double calculateCarbohydrate(UUID id) {
//        UserParam userParam = userParamRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("UserParam not found"));
//
//        int age = calculateAge(userParam.getBirthDay(), LocalDate.now());
//        double bmr = calculateBMR(userParam.getWeight(), userParam.getHeight(), age, userParam.getGender());
//        double adjustedCalories = adjustBMRBasedOnActivity(bmr, userParam.getTrainingType());
//
//        // Presupunem un procent de 55% din caloriile totale pentru necesarul de carbohidrați
//        double caloriesFromCarbs = adjustedCalories * 0.55;
//
//        // Convertim caloriile din carbohidrați în grame, știind că 1 gram de carbohidrați = 4 calorii
//        return caloriesFromCarbs / 4;
//    }
}
