package com.BodyBuddy.BodyBuddyAPI.models.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class MealFoodId implements Serializable {
    @Column(name = "meal_id")
    private UUID mealId;

    @Column(name = "food_id")
    private UUID foodId;

    public MealFoodId() {}

    public MealFoodId(
            UUID mealId,
            UUID foodId) {
        this.mealId = mealId;
        this.foodId = foodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MealFoodId that = (MealFoodId) o;
        return Objects.equals(mealId, that.mealId) &&
                Objects.equals(foodId, that.foodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId, foodId);
    }
}
