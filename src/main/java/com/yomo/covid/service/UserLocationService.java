package com.yomo.covid.service;

import com.yomo.covid.service.dto.UserLocationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.yomo.covid.domain.UserLocation}.
 */
public interface UserLocationService {

    /**
     * Save a userLocation.
     *
     * @param userLocationDTO the entity to save.
     * @return the persisted entity.
     */
    UserLocationDTO save(UserLocationDTO userLocationDTO);

    /**
     * Get all the userLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserLocationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userLocation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserLocationDTO> findOne(String id);

    /**
     * Delete the "id" userLocation.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
