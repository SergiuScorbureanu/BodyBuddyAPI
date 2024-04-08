package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.models.UserParam;
import com.BodyBuddy.BodyBuddyAPI.models.dto.UserParamDTO;
import com.BodyBuddy.BodyBuddyAPI.repositories.UserParamRepository;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.UserParamServiceImpl;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(path = "/bodybuddy/v1/user_params")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserParamController {

    private final UserParamServiceImpl userParamService;
    private final UserParamRepository userParamRepository;

    @GetMapping(path = "/{id}")
    public List<UserParam> getUserParams(@PathVariable UUID id) {
        return userParamService.getUserParams(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createUserParam(@RequestBody UserParamDTO userParamDTO) {
        UserParam userParam = userParamService.createUserParamFromDTO(userParamDTO);

        Map<String, String> response = new HashMap<>();
        response.put("message", "The user params has been saved.");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateUserParam(@PathVariable UUID id, @RequestBody UserParam updatedUser) {
        try {
            userParamService.updateUserParam(id, updatedUser);
            return ResponseEntity.ok("The user has been updated successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
