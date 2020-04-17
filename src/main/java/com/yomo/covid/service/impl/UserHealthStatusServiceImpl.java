package com.yomo.covid.service.impl;

import com.yomo.covid.service.UserHealthStatusService;
import com.yomo.covid.domain.UserHealthStatus;
import com.yomo.covid.repository.UserHealthStatusRepository;
import com.yomo.covid.service.dto.UserHealthStatusDTO;
import com.yomo.covid.service.mapper.UserHealthStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UserHealthStatus}.
 */
@Service
public class UserHealthStatusServiceImpl implements UserHealthStatusService {

    private final Logger log = LoggerFactory.getLogger(UserHealthStatusServiceImpl.class);

    private final UserHealthStatusRepository userHealthStatusRepository;

    private final UserHealthStatusMapper userHealthStatusMapper;

    public UserHealthStatusServiceImpl(UserHealthStatusRepository userHealthStatusRepository, UserHealthStatusMapper userHealthStatusMapper) {
        this.userHealthStatusRepository = userHealthStatusRepository;
        this.userHealthStatusMapper = userHealthStatusMapper;
    }

    /**
     * Save a userHealthStatus.
     *
     * @param userHealthStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserHealthStatusDTO save(UserHealthStatusDTO userHealthStatusDTO) {
        log.debug("Request to save UserHealthStatus : {}", userHealthStatusDTO);
        UserHealthStatus userHealthStatus = userHealthStatusMapper.toEntity(userHealthStatusDTO);
        userHealthStatus = userHealthStatusRepository.save(userHealthStatus);
        return userHealthStatusMapper.toDto(userHealthStatus);
    }

    /**
     * Get all the userHealthStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<UserHealthStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserHealthStatuses");
        return userHealthStatusRepository.findAll(pageable)
            .map(userHealthStatusMapper::toDto);
    }

    /**
     * Get one userHealthStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<UserHealthStatusDTO> findOne(String id) {
        log.debug("Request to get UserHealthStatus : {}", id);
        return userHealthStatusRepository.findById(id)
            .map(userHealthStatusMapper::toDto);
    }

    /**
     * Delete the userHealthStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete UserHealthStatus : {}", id);
        userHealthStatusRepository.deleteById(id);
    }
}
