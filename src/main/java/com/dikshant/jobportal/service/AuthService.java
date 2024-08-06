package com.dikshant.jobportal.service;

import com.dikshant.jobportal.dtos.UserRegistrationRequest;
import com.dikshant.jobportal.model.User;
import com.dikshant.jobportal.repository.UserRepository;
import com.dikshant.jobportal.utils.EmailValidator;
import com.dikshant.jobportal.utils.PasswordValidator;
import com.dikshant.jobportal.utils.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User userRegister(UserRegistrationRequest userRegistrationRequest) {
        validateUniqueUsernameAndEmail(userRegistrationRequest.getUsername(), userRegistrationRequest.getEmail());

        if(!PasswordValidator.isValidPassword(userRegistrationRequest.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        if(!EmailValidator.isValidEmail(userRegistrationRequest.getEmail())) {
            throw new IllegalArgumentException("Invalid email");
        }
        if(!isValidRole(userRegistrationRequest.getRole().toUpperCase())){
            throw new IllegalArgumentException("Invalid role");
        }

        User user = new User(userRegistrationRequest.getUsername(),
                        passwordEncoder.encode(userRegistrationRequest.getPassword()),
                        userRegistrationRequest.getFirstName(),
                        userRegistrationRequest.getLastName(),
                        userRegistrationRequest.getEmail(),
                        userRegistrationRequest.getPhone(),
                        userRegistrationRequest.getRole().toUpperCase());

        return userRepository.save(user);
    }

    public boolean isValidRole(String role){
         try {
             return Arrays.stream(UserRole.values()).anyMatch(r -> r == UserRole.valueOf(role));
         }
         catch (Exception e){
             return false;
         }
    }


    public void validateUniqueUsernameAndEmail(String username, String email) {
        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("Username is already in use");
        }
        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email is already in use");
        }
    }
}
