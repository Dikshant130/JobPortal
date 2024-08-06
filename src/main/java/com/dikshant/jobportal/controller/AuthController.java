package com.dikshant.jobportal.controller;

import com.dikshant.jobportal.dtos.UserRegistrationRequest;
import com.dikshant.jobportal.model.User;
import com.dikshant.jobportal.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        User user;
        try {
            user = authService.userRegister(userRegistrationRequest);
            return new ResponseEntity<>("User registered successfully: "+user.getUsername(), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Registration failed: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
