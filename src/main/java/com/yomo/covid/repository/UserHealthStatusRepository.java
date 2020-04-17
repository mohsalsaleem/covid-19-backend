package com.yomo.covid.repository;

import com.yomo.covid.domain.UserHealthStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the UserHealthStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserHealthStatusRepository extends MongoRepository<UserHealthStatus, String> {
    Page<UserHealthStatus> findByUserId(String userId, Pageable pageable);
}
