package com.yomo.covid.repository;

import com.yomo.covid.domain.UserCurrentLocation;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the UserCurrentLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserCurrentLocationRepository extends MongoRepository<UserCurrentLocation, String> {
}
