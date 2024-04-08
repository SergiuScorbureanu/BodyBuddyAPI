package com.BodyBuddy.BodyBuddyAPI.repositories;

import com.BodyBuddy.BodyBuddyAPI.models.Meal;
import com.BodyBuddy.BodyBuddyAPI.models.enums.EMealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MealRepository extends JpaRepository<Meal, UUID> {
}
