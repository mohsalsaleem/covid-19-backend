package com.yomo.covid.repository;

import com.yomo.covid.domain.UserSymptom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the UserSymptom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserSymptomRepository extends MongoRepository<UserSymptom, String> {
    Page<UserSymptom> findByUserId(String userId, Pageable pageable);
}
