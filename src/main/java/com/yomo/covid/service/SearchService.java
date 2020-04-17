package com.yomo.covid.service;

import com.yomo.covid.domain.User;
import com.yomo.covid.domain.UserHealthStatus;
import com.yomo.covid.domain.UserLocation;
import com.yomo.covid.domain.UserTravelHistory;
import com.yomo.covid.domain.enumeration.UserHealthSeverity;
import com.yomo.covid.repository.UserHealthStatusRepository;
import com.yomo.covid.repository.UserLocationRepository;
import com.yomo.covid.repository.UserRepository;
import com.yomo.covid.repository.UserTravelHistoryRepository;
import com.yomo.covid.service.dto.*;
import com.yomo.covid.service.mapper.UserHealthStatusMapper;
import com.yomo.covid.service.mapper.UserTravelHistoryMapper;
import com.yomo.covid.web.rest.AccountResource;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Search service to find user by
 * severity,
 * age group,
 * contact with someone,
 * travel history to affected areas
 */
@Service
public class SearchService {

    @Autowired
    private UserHealthStatusRepository userHealthStatusRepository;

    @Autowired
    private UserLocationRepository userLocationRepository;

    @Autowired
    private UserTravelHistoryRepository userTravelHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTravelHistoryMapper userTravelHistoryMapper;

    @Autowired
    private UserHealthStatusMapper userHealthStatusMapper;

    public List<SearchResultDTO> search(SearchDTO searchDTO) {
        // Find users by severity
        List<String> userIds = userHealthStatusRepository
            .findBySeverityIn(searchDTO.getUserHealthSeveritySet())
            .stream()
            .map(UserHealthStatus::getUserId)
            .collect(Collectors.toList());


        if(searchDTO.getAgeGroup().toAge != 1024 && searchDTO.getAgeGroup().fromAge != 1024 && searchDTO.getAgeGroup().toAge >= searchDTO.getAgeGroup().fromAge) {
            userIds = userRepository
                .findByIdInAndAgeBetween(userIds, searchDTO.getAgeGroup().toAge, searchDTO.getAgeGroup().toAge)
                .stream()
                .map(User::getId)
                .collect(Collectors.toList());
        }

        if(searchDTO.getLocationName() != null && !searchDTO.getLocationName().isEmpty()) {
            // Filter by travel history
            userIds = userTravelHistoryRepository
                .findByUserIdInAndLocationNameIgnoreCaseContaining(userIds, searchDTO.getLocationName())
                .stream()
                .map(UserTravelHistory::getUserId)
                .collect(Collectors.toList());
        }

        Map<String, UserDTO> userMap = userRepository.findByIdIn(userIds).stream().map(UserDTO::new).collect(Collectors.toMap(UserDTO::getId, user -> user));

        Map<String, List<UserTravelHistoryDTO>> userTravelHistoryDTOMap = userTravelHistoryRepository
            .findByUserIdIn(userIds)
            .stream()
            .map(userTravelHistory -> userTravelHistoryMapper.toDto(userTravelHistory))
            .collect(Collectors.groupingBy(UserTravelHistoryDTO::getUserId));

        Map<String, List<UserHealthStatusDTO>> userHealthStatusDTOMap = userHealthStatusRepository
            .findByUserIdIn(userIds)
            .stream()
            .map(userHealthStatus -> userHealthStatusMapper.toDto(userHealthStatus))
            .collect(Collectors.groupingBy(UserHealthStatusDTO::getUserId));

        List<SearchResultDTO> searchResultDTOS = new ArrayList<>();

        for (String userId : userIds) {
            SearchResultDTO searchResultDTO = new SearchResultDTO();
            searchResultDTO.setUserId(userId);
            searchResultDTO.setUserDTO(userMap.getOrDefault(userId, null));
            searchResultDTO.setUserHealthStatusDTOs(userHealthStatusDTOMap.get(userId));
            searchResultDTO.setUserTravelHistoryDTO(userTravelHistoryDTOMap.get(userId));
            searchResultDTOS.add(searchResultDTO);
        }

        return searchResultDTOS;
    }
}
