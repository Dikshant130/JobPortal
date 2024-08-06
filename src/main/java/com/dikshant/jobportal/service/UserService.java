package com.dikshant.jobportal.service;

import java.util.List;

import com.dikshant.jobportal.model.User;
import com.dikshant.jobportal.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
