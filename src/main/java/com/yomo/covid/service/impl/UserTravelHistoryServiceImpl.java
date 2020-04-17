package com.yomo.covid.service.impl;

import com.yomo.covid.service.UserTravelHistoryService;
import com.yomo.covid.domain.UserTravelHistory;
import com.yomo.covid.repository.UserTravelHistoryRepository;
import com.yomo.covid.service.dto.UserTravelHistoryDTO;
import com.yomo.covid.service.mapper.UserTravelHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UserTravelHistory}.
 */
@Service
public class UserTravelHistoryServiceImpl implements UserTravelHistoryService {

    private final Logger log = LoggerFactory.getLogger(UserTravelHistoryServiceImpl.class);

    private final UserTravelHistoryRepository userTravelHistoryRepository;

    private final UserTravelHistoryMapper userTravelHistoryMapper;

    public UserTravelHistoryServiceImpl(UserTravelHistoryRepository userTravelHistoryRepository, UserTravelHistoryMapper userTravelHistoryMapper) {
        this.userTravelHistoryRepository = userTravelHistoryRepository;
        this.userTravelHistoryMapper = userTravelHistoryMapper;
    }

    /**
     * Save a userTravelHistory.
     *
     * @param userTravelHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UserTravelHistoryDTO save(UserTravelHistoryDTO userTravelHistoryDTO) {
        log.debug("Request to save UserTravelHistory : {}", userTravelHistoryDTO);
        UserTravelHistory userTravelHistory = userTravelHistoryMapper.toEntity(userTravelHistoryDTO);
        userTravelHistory = userTravelHistoryRepository.save(userTravelHistory);
        return userTravelHistoryMapper.toDto(userTravelHistory);
    }

    /**
     * Get all the userTravelHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<UserTravelHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserTravelHistories");
        return userTravelHistoryRepository.findAll(pageable)
            .map(userTravelHistoryMapper::toDto);
    }

    /**
     * Get one userTravelHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<UserTravelHistoryDTO> findOne(String id) {
        log.debug("Request to get UserTravelHistory : {}", id);
        return userTravelHistoryRepository.findById(id)
            .map(userTravelHistoryMapper::toDto);
    }

    /**
     * Delete the userTravelHistory by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete UserTravelHistory : {}", id);
        userTravelHistoryRepository.deleteById(id);
    }
}
