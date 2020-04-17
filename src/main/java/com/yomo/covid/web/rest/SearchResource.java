package com.yomo.covid.web.rest;

import com.yomo.covid.domain.UserTravelHistory;
import com.yomo.covid.domain.enumeration.UserHealthSeverity;
import com.yomo.covid.service.SearchService;
import com.yomo.covid.service.dto.*;
import io.github.jhipster.web.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class SearchResource {

    private final SearchService searchService;

    public SearchResource(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchResultDTO>> search(
        @RequestParam(required = false) Set<UserHealthSeverity> userHealthSeveritySet,
        @RequestParam(required = false) Set<UserTravelHistoryDTO> userTravelHistoryDTOSet,
        @RequestParam(required = false) String locationName,
        @RequestParam(required = false, defaultValue = "1024") Integer fromAge,
        @RequestParam(required = false, defaultValue = "1024") Integer toAge,
        Pageable pageable
    ) {
        SearchDTO searchDTO = new SearchDTO();

        AgeGroup ageGroup = new AgeGroup();
        ageGroup.fromAge = fromAge;
        ageGroup.toAge = toAge;
        searchDTO.setAgeGroup(ageGroup);

        searchDTO.setLocationName(locationName);
        searchDTO.setUserHealthSeveritySet(userHealthSeveritySet);

        List<SearchResultDTO> result = searchService.search(searchDTO);
        return ResponseEntity.ok().body(result);
    }
}
