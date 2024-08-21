package com.BodyBuddy.BodyBuddyAPI.models.dto;

import com.BodyBuddy.BodyBuddyAPI.models.enums.EGender;
import com.BodyBuddy.BodyBuddyAPI.models.enums.ETrainingType;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EWeightChangeRate;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EWeightGoal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private UUID id;
    private String username;
    private String email;
    private String picture;
    private String role;
    private LocalDate birthDay;
    private ETrainingType trainingType;
    private EWeightGoal weightGoal;
    private EGender gender;
    private Double weight;
    private Double height;
    private EWeightChangeRate weightChangeRate;
}
