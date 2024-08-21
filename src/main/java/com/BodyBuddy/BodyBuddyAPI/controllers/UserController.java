package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.models.User;
import com.BodyBuddy.BodyBuddyAPI.models.dto.UserDTO;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/bodybuddy/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/{id}/username")
    public ResponseEntity<Map<String, String>> updateUsername(@PathVariable UUID id, @RequestBody Map<String, String> payload) {
        try {
            userService.updateUsername(id, payload.get("username"));
            Map<String, String> response = new HashMap<>();
            response.put("message", "Username updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<Map<String, String>> updateEmail(@PathVariable UUID id, @RequestBody Map<String, String> payload) {
        try {
            userService.updateEmail(id, payload.get("email"));
            Map<String, String> response = new HashMap<>();
            response.put("message", "Email updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Map<String, String>> updatePassword(@PathVariable UUID id, @RequestBody Map<String, String> payload) {
        try {
            userService.updatePassword(id, payload.get("password"));
            Map<String, String> response = new HashMap<>();
            response.put("message", "Password updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping
//    public void createUser(@RequestBody User user) {
//        userService.createUser(user);
//    }

//    @PutMapping(path = "/{id}")
//    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
//        try {
//            userService.updateUser(id, updatedUser);
//            return ResponseEntity.ok("The user has been updated successfully!");
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable UUID id) {
        Map<String, String> response = new HashMap<>();
        try {
            userService.deleteUser(id);
            response.put("message", "The user has been deleted successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            response.put("message", "User not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
