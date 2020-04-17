package com.yomo.covid.service;

import com.yomo.covid.domain.UserSymptom;
import com.yomo.covid.service.dto.UserSymptomDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.yomo.covid.domain.UserSymptom}.
 */
public interface UserSymptomService {

    /**
     * Save a userSymptom.
     *
     * @param userSymptomDTO the entity to save.
     * @return the persisted entity.
     */
    UserSymptomDTO save(UserSymptomDTO userSymptomDTO);

    /**
     * Get all the userSymptoms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserSymptomDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userSymptom.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserSymptomDTO> findOne(String id);

    /**
     * Delete the "id" userSymptom.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    Page<UserSymptomDTO> findByUserId(String userId, Pageable pageable);
}
