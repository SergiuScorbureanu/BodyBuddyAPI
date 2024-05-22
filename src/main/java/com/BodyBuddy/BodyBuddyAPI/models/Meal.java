package com.BodyBuddy.BodyBuddyAPI.models;

import com.BodyBuddy.BodyBuddyAPI.models.abstracts.BaseEntity;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EMealType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Entity(name = "Meal")
@Table(name = "meal")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Meal extends BaseEntity {
    @JsonManagedReference
    @OneToMany(
            mappedBy = "meal",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<MealFood> foods = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(name = "meal_type")
    @Enumerated(EnumType.STRING)
    private EMealType mealType;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "carbohydrates")
    private Double carbohydrates ;

    @Column(name = "fat")
    private Double fat;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "date")
    private String date;

    public void addFood(Food food, int quantity) {
        MealFood mealFood = new MealFood(this, food, quantity);
        foods.add(mealFood);
        food.getMeals().add(mealFood);
    }

    public void removeFood(Food food) {
        for (Iterator<MealFood> iterator = foods.iterator();
             iterator.hasNext(); ) {
            MealFood mealFood = iterator.next();

            if (mealFood.getMeal().equals(this) &&
                    mealFood.getFood().equals(food)) {
                iterator.remove();
                mealFood.getFood().getMeals().remove(mealFood);
                mealFood.setMeal(null);
                mealFood.setFood(null);
            }
        }
    }

}
