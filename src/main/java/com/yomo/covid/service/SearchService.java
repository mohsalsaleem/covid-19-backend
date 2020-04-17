package com.yomo.covid.service;

import com.yomo.covid.service.dto.SearchDTO;
import com.yomo.covid.service.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Search service to find user by
 * severity,
 * age group,
 * contact with someone,
 * travel history to affected areas
 */
@Service
public class SearchService {
    public Page<UserDTO> search(SearchDTO searchDTO) {
        return Page.empty();
    }
}
