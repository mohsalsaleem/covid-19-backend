package com.yomo.covid.service.impl;

import com.yomo.covid.service.UserSymptomService;
import com.yomo.covid.domain.UserSymptom;
import com.yomo.covid.repository.UserSymptomRepository;
import com.yomo.covid.service.dto.UserSymptomDTO;
import com.yomo.covid.service.mapper.UserSymptomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UserSymptom}.
 */
@Service
public class UserSymptomServiceImpl implements UserSymptomService {

    private final Logger log = LoggerFactory.getLogger(UserSymptomServiceImpl.class);

    private final UserSymptomRepository userSymptomRepository;

    private final UserSymptomMapper userSymptomMapper;

    public UserSymptomServiceImpl(UserSymptomRepository userSymptomRepository, UserSymptomMapper userSymptomMapper) {
        this.userSymptomRepository = userSymptomRepository;
        this.userSymptomMapper = userSymptomMapper;
    }

    /**
     * Save a userSymptom.
     *
     * @param userSymptomDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserSymptomDTO save(UserSymptomDTO userSymptomDTO) {
        log.debug("Request to save UserSymptom : {}", userSymptomDTO);
        UserSymptom userSymptom = userSymptomMapper.toEntity(userSymptomDTO);
        userSymptom = userSymptomRepository.save(userSymptom);
        return userSymptomMapper.toDto(userSymptom);
    }

    /**
     * Get all the userSymptoms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<UserSymptomDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserSymptoms");
        return userSymptomRepository.findAll(pageable)
            .map(userSymptomMapper::toDto);
    }

    /**
     * Get one userSymptom by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<UserSymptomDTO> findOne(String id) {
        log.debug("Request to get UserSymptom : {}", id);
        return userSymptomRepository.findById(id)
            .map(userSymptomMapper::toDto);
    }

    /**
     * Delete the userSymptom by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete UserSymptom : {}", id);
        userSymptomRepository.deleteById(id);
    }

    @Override
    public Page<UserSymptomDTO> findByUserId(String userId, Pageable pageable) {
        return userSymptomRepository.findByUserId(userId, pageable).map(userSymptomMapper::toDto);
    }
}
