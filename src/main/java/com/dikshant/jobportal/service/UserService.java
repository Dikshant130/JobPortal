package com.dikshant.jobportal.service;

import java.util.List;
import com.dikshant.jobportal.dtos.UserRegistrationRequest;
import com.dikshant.jobportal.model.User;
import com.dikshant.jobportal.repository.UserRepository;
import com.dikshant.jobportal.utils.EmailValidator;
import com.dikshant.jobportal.utils.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User registerUser(UserRegistrationRequest userRegistrationRequest) {

        User user = new User();

        if(userRepository.findByUsername(userRegistrationRequest.getUsername())!=null){
            throw new IllegalArgumentException("Username is already in use");
        }
        user.setUsername(userRegistrationRequest.getUsername());

        if(!PasswordValidator.isValidPassword(userRegistrationRequest.getPassword())){
            throw new IllegalArgumentException("Invalid Password");
        }
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));


        if(!EmailValidator.isValidEmail(userRegistrationRequest.getEmail())){
            throw new IllegalArgumentException("Invalid email format");
        }
        else if(userRepository.findByEmail(userRegistrationRequest.getEmail())!=null){
            throw new IllegalArgumentException("Email is already registered");
        }
        user.setEmail(userRegistrationRequest.getEmail());


        user.setFirstName(userRegistrationRequest.getFirstName());
        user.setLastName(userRegistrationRequest.getLastName());
        user.setRole(userRegistrationRequest.getRole());
        user.setPhone(userRegistrationRequest.getPhone());

        return userRepository.save(user);
    }
}
