package com.BodyBuddy.BodyBuddyAPI.services.implementations;

import com.BodyBuddy.BodyBuddyAPI.models.Role;
import com.BodyBuddy.BodyBuddyAPI.models.User;
import com.BodyBuddy.BodyBuddyAPI.models.UserParam;
import com.BodyBuddy.BodyBuddyAPI.models.dto.UserDTO;
import com.BodyBuddy.BodyBuddyAPI.models.enums.ERole;
import com.BodyBuddy.BodyBuddyAPI.repositories.RoleRepository;
import com.BodyBuddy.BodyBuddyAPI.repositories.UserRepository;
import com.BodyBuddy.BodyBuddyAPI.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(user -> {
            String role = user.getRole().getName().name();

            UserParam userParam = user.getUserParams().stream().findFirst().orElse(null);

            return new UserDTO(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPicture(),
                    role,
                    userParam != null ? userParam.getBirthDay() : null,
                    userParam != null ? userParam.getTrainingType() : null,
                    userParam != null ? userParam.getWeightGoal() : null,
                    userParam != null ? userParam.getGender() : null,
                    userParam != null ? userParam.getWeight() : null,
                    userParam != null ? userParam.getHeight() : null,
                    userParam != null ? userParam.getWeightChangeRate() : null
            );
        }).collect(Collectors.toList());
    }

    @Override
    public void updateUsername(UUID id, String username) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setUsername(username);
        userRepository.save(user);
    }

    @Override
    public void updateEmail(UUID id, String email) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void updatePassword(UUID id, String password) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
        } else {
            throw new NoSuchElementException("The user with the id " + id + " doesn't exist in the database.");
        }
    }

    @Transactional
    public User saveOrUpdateUser(String name, String email, String picture, ERole role) {
        try {
            User user = userRepository.findByEmail(email).orElse(new User());
            user.setUsername(name);
            user.setEmail(email);
            user.setPicture(picture);

            Role userRole = roleRepository.findByName(role)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            user.setRole(userRole);

            return userRepository.save(user);
        } catch (Exception e) {
            System.err.println("Error saving or updating user: " + e.getMessage());
            throw e;
        }
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

//    @Override
//    public void createUser(User user) {
//        String userEmail = user.getEmail();
//        String userPhone = user.getPhone();
//
//        if (!isEmailValid(userEmail))
//            throw new RuntimeException("Email is not in a valid format");
//        if (!isPhoneValid(userPhone))
//            throw new RuntimeException("Phone number is not in a valid format");
//        userRepository.save(user);
//    }

//    @Override
//    public void updateUser(UUID id, User updatedUser) {
//        Optional<User> optionalUser = userRepository.findById(id);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setBirthDay(updatedUser.getBirthDay());
//            user.setTrainingType(updatedUser.getTrainingType());
//            user.setGender(updatedUser.getGender());
//            user.setWeight(updatedUser.getWeight());
//            user.setHeight(updatedUser.getHeight());
//            user.setWeightGoal(updatedUser.getWeightGoal());
//            userRepository.save(user);
//
//        } else {
//            throw new NoSuchElementException("The user with the id " + " doesn't exist in the database.");
//        }
//    }


//    public static boolean isEmailValid (String emailAddress) {
//        return Pattern.compile("^(.+)@(\\S+)$")
//                .matcher(emailAddress)
//                .matches();
//    }
//
//    public static boolean isPhoneValid (String phoneNumber) {
//        return Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
//                .matcher(phoneNumber)
//                .matches();
//    }
}
