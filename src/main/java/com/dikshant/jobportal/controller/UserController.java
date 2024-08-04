package com.dikshant.jobportal.controller;

import com.dikshant.jobportal.dtos.UserRegistrationRequest;
import com.dikshant.jobportal.model.User;
import com.dikshant.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/auth")
@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        try {
            User user = userService.registerUser(userRegistrationRequest);
            return new ResponseEntity<>("User registered successfully: "+ user.getUsername(), HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions (e.g., duplicate username, invalid email)
            return new ResponseEntity<>("Registration failed: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
