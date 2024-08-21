package com.BodyBuddy.BodyBuddyAPI.models;

import com.BodyBuddy.BodyBuddyAPI.models.abstracts.BaseEntity;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EGender;
import com.BodyBuddy.BodyBuddyAPI.models.enums.ETrainingType;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EWeightChangeRate;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EWeightGoal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "user_params")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserParam extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User userId;

    @Column(name = "birth_day") //, nullable = false)
    private LocalDate birthDay;

    @Column(name = "training_type")
    @Enumerated(EnumType.STRING)
    private ETrainingType trainingType;

    @Column(name = "weight_goal")
    @Enumerated(EnumType.STRING)
    private EWeightGoal weightGoal;

    @Column(name = "weight_change_rate")
    @Enumerated(EnumType.STRING)
    private EWeightChangeRate weightChangeRate;

    @Column(name = "gender") //, nullable = false)
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Column(name = "weight") //, nullable = false)
    private Double weight;

    @Column(name = "height") //, nullable = false)
    private Double height;
}
