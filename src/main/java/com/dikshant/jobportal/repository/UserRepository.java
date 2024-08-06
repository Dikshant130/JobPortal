package com.dikshant.jobportal.repository;

import com.dikshant.jobportal.model.User;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

@Document("users")
public interface UserRepository extends MongoRepository<User, String> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
