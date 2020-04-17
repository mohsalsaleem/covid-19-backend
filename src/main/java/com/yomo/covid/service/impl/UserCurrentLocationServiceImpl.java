package com.yomo.covid.service.impl;

import com.yomo.covid.service.UserCurrentLocationService;
import com.yomo.covid.domain.UserCurrentLocation;
import com.yomo.covid.repository.UserCurrentLocationRepository;
import com.yomo.covid.service.dto.UserCurrentLocationDTO;
import com.yomo.covid.service.mapper.UserCurrentLocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UserCurrentLocation}.
 */
@Service
public class UserCurrentLocationServiceImpl implements UserCurrentLocationService {

    private final Logger log = LoggerFactory.getLogger(UserCurrentLocationServiceImpl.class);

    private final UserCurrentLocationRepository userCurrentLocationRepository;

    private final UserCurrentLocationMapper userCurrentLocationMapper;

    public UserCurrentLocationServiceImpl(UserCurrentLocationRepository userCurrentLocationRepository, UserCurrentLocationMapper userCurrentLocationMapper) {
        this.userCurrentLocationRepository = userCurrentLocationRepository;
        this.userCurrentLocationMapper = userCurrentLocationMapper;
    }

    /**
     * Save a userCurrentLocation.
     *
     * @param userCurrentLocationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserCurrentLocationDTO save(UserCurrentLocationDTO userCurrentLocationDTO) {
        log.debug("Request to save UserCurrentLocation : {}", userCurrentLocationDTO);
        UserCurrentLocation userCurrentLocation = userCurrentLocationMapper.toEntity(userCurrentLocationDTO);
        userCurrentLocation = userCurrentLocationRepository.save(userCurrentLocation);
        return userCurrentLocationMapper.toDto(userCurrentLocation);
    }

    /**
     * Get all the userCurrentLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<UserCurrentLocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserCurrentLocations");
        return userCurrentLocationRepository.findAll(pageable)
            .map(userCurrentLocationMapper::toDto);
    }

    /**
     * Get one userCurrentLocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<UserCurrentLocationDTO> findOne(String id) {
        log.debug("Request to get UserCurrentLocation : {}", id);
        return userCurrentLocationRepository.findById(id)
            .map(userCurrentLocationMapper::toDto);
    }

    /**
     * Delete the userCurrentLocation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete UserCurrentLocation : {}", id);
        userCurrentLocationRepository.deleteById(id);
    }
}
