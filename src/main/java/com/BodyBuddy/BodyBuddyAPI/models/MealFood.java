package com.BodyBuddy.BodyBuddyAPI.models;

import com.BodyBuddy.BodyBuddyAPI.models.embeddables.MealFoodId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity(name = "MealFood")
@Table(name = "meal_food")
@Getter
@Setter
@RequiredArgsConstructor
public class MealFood {
    @EmbeddedId
    private MealFoodId id;

    @JsonBackReference
    @ManyToOne()
    @MapsId("mealId")
    private Meal meal;

    @ManyToOne()
    @MapsId("foodId")
    private Food food;

    @Column(name = "quantity")
    public int quantity = 0;

    public MealFood(Meal meal, Food food, int quantity) {
        this.meal = meal;
        this.food = food;
        this.id = new MealFoodId(meal.getId(), food.getId());
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MealFood that = (MealFood) o;
        return Objects.equals(meal, that.meal) &&
                Objects.equals(food, that.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meal, food);
    }
}
