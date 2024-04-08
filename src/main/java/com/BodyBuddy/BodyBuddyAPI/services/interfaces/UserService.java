package com.BodyBuddy.BodyBuddyAPI.services.interfaces;

import com.BodyBuddy.BodyBuddyAPI.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public List<User> getUsers();

    //public void createUser(User user);

    //public void updateUser(UUID id, User updatedUser);

    public void deleteUser(UUID id);



}
