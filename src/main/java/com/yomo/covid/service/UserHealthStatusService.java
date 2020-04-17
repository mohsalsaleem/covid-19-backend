package com.yomo.covid.service;

import com.yomo.covid.service.dto.UserHealthStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.yomo.covid.domain.UserHealthStatus}.
 */
public interface UserHealthStatusService {

    /**
     * Save a userHealthStatus.
     *
     * @param userHealthStatusDTO the entity to save.
     * @return the persisted entity.
     */
    UserHealthStatusDTO save(UserHealthStatusDTO userHealthStatusDTO);

    /**
     * Get all the userHealthStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserHealthStatusDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userHealthStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserHealthStatusDTO> findOne(String id);

    /**
     * Delete the "id" userHealthStatus.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    Page<UserHealthStatusDTO> findByUserId(String userId, Pageable pageable);
}
