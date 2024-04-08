package com.BodyBuddy.BodyBuddyAPI.models.dto;

import com.BodyBuddy.BodyBuddyAPI.models.enums.EGender;
import com.BodyBuddy.BodyBuddyAPI.models.enums.ETrainingType;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EWeightGoal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserParamDTO {

    private String userId;
    private LocalDate birthDay;
    private EGender gender;
    private Double height;
    private Double weight;
    private ETrainingType trainingType;
    private EWeightGoal weightGoal;
}
