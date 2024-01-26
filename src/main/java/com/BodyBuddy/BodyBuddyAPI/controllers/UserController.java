package com.BodyBuddy.BodyBuddyAPI.controllers;

import com.BodyBuddy.BodyBuddyAPI.models.User;
import com.BodyBuddy.BodyBuddyAPI.services.implementations.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/bodybuddy/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        try {
            userService.updateUser(id, updatedUser);
            return ResponseEntity.ok("The user has been updated successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("The user has been deleted successfully!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
