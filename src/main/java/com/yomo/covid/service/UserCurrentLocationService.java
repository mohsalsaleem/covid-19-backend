package com.yomo.covid.service;

import com.yomo.covid.domain.UserCurrentLocation;
import com.yomo.covid.service.dto.UserCurrentLocationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.yomo.covid.domain.UserCurrentLocation}.
 */
public interface UserCurrentLocationService {

    /**
     * Save a userCurrentLocation.
     *
     * @param userCurrentLocationDTO the entity to save.
     * @return the persisted entity.
     */
    UserCurrentLocationDTO save(UserCurrentLocationDTO userCurrentLocationDTO);

    /**
     * Get all the userCurrentLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserCurrentLocationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userCurrentLocation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserCurrentLocationDTO> findOne(String id);

    /**
     * Delete the "id" userCurrentLocation.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    Page<UserCurrentLocationDTO> findByUserId(String userId, Pageable pageable);
}
