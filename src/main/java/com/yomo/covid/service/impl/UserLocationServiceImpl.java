package com.yomo.covid.service.impl;

import com.yomo.covid.domain.Location;
import com.yomo.covid.service.UserLocationService;
import com.yomo.covid.domain.UserLocation;
import com.yomo.covid.repository.UserLocationRepository;
import com.yomo.covid.service.dto.UserLocationDTO;
import com.yomo.covid.service.mapper.UserLocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * Service Implementation for managing {@link UserLocation}.
 */
@Service
public class UserLocationServiceImpl implements UserLocationService {

    private final Logger log = LoggerFactory.getLogger(UserLocationServiceImpl.class);

    private final UserLocationRepository userLocationRepository;

    private final UserLocationMapper userLocationMapper;

    public UserLocationServiceImpl(UserLocationRepository userLocationRepository, UserLocationMapper userLocationMapper) {
        this.userLocationRepository = userLocationRepository;
        this.userLocationMapper = userLocationMapper;
    }

    /**
     * Save a userLocation.
     *
     * @param userLocationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserLocationDTO save(UserLocationDTO userLocationDTO) {
        log.debug("Request to save UserLocation : {}", userLocationDTO);
        UserLocation userLocation = userLocationMapper.toEntity(userLocationDTO);

        Location location = new Location();
        location.setCoordinates(Arrays.asList(userLocationDTO.getLatitude(), userLocationDTO.getLongitude()));
        location.setType("Point");
        userLocation.location = location;

        userLocation = userLocationRepository.save(userLocation);
        return userLocationMapper.toDto(userLocation);
    }

    /**
     * Get all the userLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<UserLocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserLocations");
        return userLocationRepository.findAll(pageable)
            .map(userLocationMapper::toDto);
    }

    /**
     * Get one userLocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<UserLocationDTO> findOne(String id) {
        log.debug("Request to get UserLocation : {}", id);
        return userLocationRepository.findById(id)
            .map(userLocationMapper::toDto);
    }

    /**
     * Delete the userLocation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete UserLocation : {}", id);
        userLocationRepository.deleteById(id);
    }

    @Override
    public Page<UserLocationDTO> findByUserId(String userId, Pageable pageable) {
        return userLocationRepository.findByUserId(userId, pageable).map(userLocationMapper::toDto);
    }
}
