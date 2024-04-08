package com.BodyBuddy.BodyBuddyAPI.services.implementations;

import com.BodyBuddy.BodyBuddyAPI.models.User;
import com.BodyBuddy.BodyBuddyAPI.repositories.UserRepository;
import com.BodyBuddy.BodyBuddyAPI.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
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

    @Override
    public void deleteUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
        } else {
            throw new NoSuchElementException("The user with the id " + " doesn't exist in the database.");
        }
    }

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
