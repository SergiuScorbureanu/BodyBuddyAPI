package com.BodyBuddy.BodyBuddyAPI.services.interfaces;

import com.BodyBuddy.BodyBuddyAPI.models.User;
import com.BodyBuddy.BodyBuddyAPI.models.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public List<UserDTO> getUsers();

    public void updateUsername(UUID id, String username);

    public void updateEmail(UUID id, String email);

    public void updatePassword(UUID id, String password);

    public void deleteUser(UUID id);

}
