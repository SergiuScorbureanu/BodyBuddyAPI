package com.BodyBuddy.BodyBuddyAPI.models;

import com.BodyBuddy.BodyBuddyAPI.models.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Entity(name = "Food")
@Table(name = "food")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Food extends BaseEntity {

    @OneToMany(
            mappedBy = "food",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<MealFood> meals = new ArrayList<>();

    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "carbohydrates")
    private Double carbohydrates ;

    @Column(name = "fat")
    private Double fat;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "measurement_unit")
    private String measurementUnit;

    @Column(name = "quantity")
    private Double quantity;
}
