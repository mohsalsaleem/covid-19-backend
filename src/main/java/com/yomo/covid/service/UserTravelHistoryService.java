package com.yomo.covid.service;

import com.yomo.covid.service.dto.UserTravelHistoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.yomo.covid.domain.UserTravelHistory}.
 */
public interface UserTravelHistoryService {

    /**
     * Save a userTravelHistory.
     *
     * @param userTravelHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    UserTravelHistoryDTO save(UserTravelHistoryDTO userTravelHistoryDTO);

    /**
     * Get all the userTravelHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserTravelHistoryDTO> findAll(Pageable pageable);

    Page<UserTravelHistoryDTO> findByUserId(String userId, Pageable pageable);

    /**
     * Get the "id" userTravelHistory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserTravelHistoryDTO> findOne(String id);

    /**
     * Delete the "id" userTravelHistory.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
