package com.dikshant.jobportal.repository;

import com.dikshant.jobportal.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job, String> {
}
