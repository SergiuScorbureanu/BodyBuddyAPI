package com.BodyBuddy.BodyBuddyAPI.services.interfaces;

import com.BodyBuddy.BodyBuddyAPI.models.UserParam;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EGender;
import com.BodyBuddy.BodyBuddyAPI.models.enums.ETrainingType;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EWeightChangeRate;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EWeightGoal;

import java.time.LocalDate;
import java.util.UUID;

public interface NutritionalCalculatorService {

    public int calculateCalories(UUID id);

    public double adjustCaloriesBasedOnWeightGoal(double calories, EWeightGoal weightGoal, EWeightChangeRate weightChangeRate);

    public int calculateAge(LocalDate birthDate, LocalDate currentDate);

    public double calculateBMR(double weight, double height, int age, EGender EGender);

    public double adjustBMRBasedOnActivity(double bmr, ETrainingType ETrainingType);

    public double calculateProtein(UUID id);

    public double calculateFat(UUID id);

    double calculateCarbohydrate(UUID id);

    public UserParam getUserParamForUser(UUID userId);

    public double getProteinRequirementPerKg(ETrainingType trainingType);
}
