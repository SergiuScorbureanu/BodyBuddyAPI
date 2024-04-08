package com.BodyBuddy.BodyBuddyAPI.repositories;

import com.BodyBuddy.BodyBuddyAPI.models.Role;
import com.BodyBuddy.BodyBuddyAPI.models.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(ERole name);
}
