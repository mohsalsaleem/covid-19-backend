package com.yomo.covid.web.rest;

import com.yomo.covid.service.UserTravelHistoryService;
import com.yomo.covid.web.rest.errors.BadRequestAlertException;
import com.yomo.covid.service.dto.UserTravelHistoryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.yomo.covid.domain.UserTravelHistory}.
 */
@RestController
@RequestMapping("/api")
public class UserTravelHistoryResource {

    private final Logger log = LoggerFactory.getLogger(UserTravelHistoryResource.class);

    private static final String ENTITY_NAME = "userTravelHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserTravelHistoryService userTravelHistoryService;

    public UserTravelHistoryResource(UserTravelHistoryService userTravelHistoryService) {
        this.userTravelHistoryService = userTravelHistoryService;
    }

    /**
     * {@code POST  /user-travel-histories} : Create a new userTravelHistory.
     *
     * @param userTravelHistoryDTO the userTravelHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userTravelHistoryDTO, or with status {@code 400 (Bad Request)} if the userTravelHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-travel-histories")
    public ResponseEntity<UserTravelHistoryDTO> createUserTravelHistory(@Valid @RequestBody UserTravelHistoryDTO userTravelHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save UserTravelHistory : {}", userTravelHistoryDTO);
        if (userTravelHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new userTravelHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserTravelHistoryDTO result = userTravelHistoryService.save(userTravelHistoryDTO);
        return ResponseEntity.created(new URI("/api/user-travel-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-travel-histories} : Updates an existing userTravelHistory.
     *
     * @param userTravelHistoryDTO the userTravelHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userTravelHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the userTravelHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userTravelHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-travel-histories")
    public ResponseEntity<UserTravelHistoryDTO> updateUserTravelHistory(@Valid @RequestBody UserTravelHistoryDTO userTravelHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update UserTravelHistory : {}", userTravelHistoryDTO);
        if (userTravelHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserTravelHistoryDTO result = userTravelHistoryService.save(userTravelHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userTravelHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-travel-histories} : get all the userTravelHistories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userTravelHistories in body.
     */
    @GetMapping("/user-travel-histories")
    public ResponseEntity<List<UserTravelHistoryDTO>> getAllUserTravelHistories(Pageable pageable) {
        log.debug("REST request to get a page of UserTravelHistories");
        Page<UserTravelHistoryDTO> page = userTravelHistoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-travel-histories} : get all the userTravelHistories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userTravelHistories in body.
     */
    @GetMapping("/user-travel-histories/user/{userId}")
    public ResponseEntity<List<UserTravelHistoryDTO>> getUserTravelHistoriesByUserId(@RequestParam String userId, Pageable pageable) {
        log.debug("REST request to get a page of UserTravelHistories");
        Page<UserTravelHistoryDTO> page = userTravelHistoryService.findByUserId(userId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-travel-histories/:id} : get the "id" userTravelHistory.
     *
     * @param id the id of the userTravelHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userTravelHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-travel-histories/{id}")
    public ResponseEntity<UserTravelHistoryDTO> getUserTravelHistory(@PathVariable String id) {
        log.debug("REST request to get UserTravelHistory : {}", id);
        Optional<UserTravelHistoryDTO> userTravelHistoryDTO = userTravelHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userTravelHistoryDTO);
    }

    /**
     * {@code DELETE  /user-travel-histories/:id} : delete the "id" userTravelHistory.
     *
     * @param id the id of the userTravelHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-travel-histories/{id}")
    public ResponseEntity<Void> deleteUserTravelHistory(@PathVariable String id) {
        log.debug("REST request to delete UserTravelHistory : {}", id);
        userTravelHistoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
