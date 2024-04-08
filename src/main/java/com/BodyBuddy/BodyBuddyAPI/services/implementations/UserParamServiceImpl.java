package com.BodyBuddy.BodyBuddyAPI.services.implementations;

import com.BodyBuddy.BodyBuddyAPI.models.User;
import com.BodyBuddy.BodyBuddyAPI.models.UserParam;
import com.BodyBuddy.BodyBuddyAPI.models.dto.UserParamDTO;
import com.BodyBuddy.BodyBuddyAPI.repositories.UserParamRepository;
import com.BodyBuddy.BodyBuddyAPI.repositories.UserRepository;
import com.BodyBuddy.BodyBuddyAPI.services.interfaces.UserParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserParamServiceImpl implements UserParamService {

    private final UserParamRepository userParamRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserParam> getUserParams(UUID id) {
        return new ArrayList<>(userParamRepository.findAllById(id));
    }


    @Override
    public UserParam createUserParamFromDTO(UserParamDTO userParamDTO) {
        UserParam userParam = new UserParam();

        // Fetch the User entity based on userId from the DTO
        User user = userRepository.findById(UUID.fromString(userParamDTO.getUserId()))
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userParamDTO.getUserId()));

        userParam.setUserId(user);
        userParam.setBirthDay(userParamDTO.getBirthDay());
        userParam.setGender(userParamDTO.getGender());
        userParam.setHeight(userParamDTO.getHeight());
        userParam.setWeight(userParamDTO.getWeight());
        userParam.setTrainingType(userParamDTO.getTrainingType());
        userParam.setWeightGoal(userParamDTO.getWeightGoal());

        userParam = userParamRepository.save(userParam);
        return userParam;
    }

    @Override
    public void updateUserParam(UUID id, UserParam updatedUser) {
        Optional<UserParam> optionalUser = userParamRepository.findById(id);

        if (optionalUser.isPresent()) {
            UserParam userParam = optionalUser.get();
            userParam.setBirthDay(updatedUser.getBirthDay());
            userParam.setTrainingType(updatedUser.getTrainingType());
            userParam.setGender(updatedUser.getGender());
            userParam.setWeight(updatedUser.getWeight());
            userParam.setHeight(updatedUser.getHeight());
            userParam.setWeightGoal(updatedUser.getWeightGoal());
            userParamRepository.save(userParam);

        } else {
            throw new NoSuchElementException("The user with the id " + " doesn't exist in the database.");
        }
    }

//    @Override
//    public void deleteUser(UUID id) {
//        Optional<User> optionalUser = userRepository.findById(id);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            userRepository.delete(user);
//        } else {
//            throw new NoSuchElementException("The user with the id " + " doesn't exist in the database.");
//        }
//    }
}
