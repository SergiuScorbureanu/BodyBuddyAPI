package com.BodyBuddy.BodyBuddyAPI.repositories;

import com.BodyBuddy.BodyBuddyAPI.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
}
