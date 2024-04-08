package com.BodyBuddy.BodyBuddyAPI.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/bodybuddy/v1/home")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {

    @GetMapping
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome!");

        return response;
    }
}
