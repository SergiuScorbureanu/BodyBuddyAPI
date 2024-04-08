package com.BodyBuddy.BodyBuddyAPI.repositories;

import com.BodyBuddy.BodyBuddyAPI.models.UserParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface UserParamRepository extends JpaRepository<UserParam, UUID> {
    Collection<? extends UserParam> findAllById(UUID id);
}
