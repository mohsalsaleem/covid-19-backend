package com.yomo.covid.repository;

import com.yomo.covid.domain.UserTravelHistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the UserTravelHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserTravelHistoryRepository extends MongoRepository<UserTravelHistory, String> {
    Page<UserTravelHistory> findByUserId(String userId, Pageable pageable);

    List<UserTravelHistory> findByUserIdInAndLocationNameIgnoreCaseContaining(List<String> userIds, String locationName);

    List<UserTravelHistory> findByUserIdIn(List<String> userId);
}
