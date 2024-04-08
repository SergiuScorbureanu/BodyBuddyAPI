package com.BodyBuddy.BodyBuddyAPI.models;

import com.BodyBuddy.BodyBuddyAPI.models.abstracts.AbstractEntity;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EMealType;
import com.BodyBuddy.BodyBuddyAPI.models.enums.ETrainingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meals")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Meal extends AbstractEntity {


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "meal_foods",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private Set<Food> foods = new HashSet<>();

    @Column(name = "meal_type")
    @Enumerated(EnumType.STRING)
    private EMealType mealType;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "carbohydrates ")
    private Double carbohydrates ;

    @Column(name = "fat")
    private Double fat;

    @Column(name = "protein")
    private Double protein;

}
