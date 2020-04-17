package com.yomo.covid.web.rest;

import com.yomo.covid.service.UserCurrentLocationService;
import com.yomo.covid.web.rest.errors.BadRequestAlertException;
import com.yomo.covid.service.dto.UserCurrentLocationDTO;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.yomo.covid.domain.UserCurrentLocation}.
 */
@RestController
@RequestMapping("/api")
public class UserCurrentLocationResource {

    private final Logger log = LoggerFactory.getLogger(UserCurrentLocationResource.class);

    private static final String ENTITY_NAME = "userCurrentLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserCurrentLocationService userCurrentLocationService;

    public UserCurrentLocationResource(UserCurrentLocationService userCurrentLocationService) {
        this.userCurrentLocationService = userCurrentLocationService;
    }

    /**
     * {@code POST  /user-current-locations} : Create a new userCurrentLocation.
     *
     * @param userCurrentLocationDTO the userCurrentLocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userCurrentLocationDTO, or with status {@code 400 (Bad Request)} if the userCurrentLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-current-locations")
    public ResponseEntity<UserCurrentLocationDTO> createUserCurrentLocation(@RequestBody UserCurrentLocationDTO userCurrentLocationDTO) throws URISyntaxException {
        log.debug("REST request to save UserCurrentLocation : {}", userCurrentLocationDTO);
        if (userCurrentLocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new userCurrentLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserCurrentLocationDTO result = userCurrentLocationService.save(userCurrentLocationDTO);
        return ResponseEntity.created(new URI("/api/user-current-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-current-locations} : Updates an existing userCurrentLocation.
     *
     * @param userCurrentLocationDTO the userCurrentLocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userCurrentLocationDTO,
     * or with status {@code 400 (Bad Request)} if the userCurrentLocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userCurrentLocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-current-locations")
    public ResponseEntity<UserCurrentLocationDTO> updateUserCurrentLocation(@RequestBody UserCurrentLocationDTO userCurrentLocationDTO) throws URISyntaxException {
        log.debug("REST request to update UserCurrentLocation : {}", userCurrentLocationDTO);
        if (userCurrentLocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserCurrentLocationDTO result = userCurrentLocationService.save(userCurrentLocationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userCurrentLocationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-current-locations} : get all the userCurrentLocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userCurrentLocations in body.
     */
    @GetMapping("/user-current-locations")
    public ResponseEntity<List<UserCurrentLocationDTO>> getAllUserCurrentLocations(Pageable pageable) {
        log.debug("REST request to get a page of UserCurrentLocations");
        Page<UserCurrentLocationDTO> page = userCurrentLocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-current-locations/:id} : get the "id" userCurrentLocation.
     *
     * @param id the id of the userCurrentLocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userCurrentLocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-current-locations/{id}")
    public ResponseEntity<UserCurrentLocationDTO> getUserCurrentLocation(@PathVariable String id) {
        log.debug("REST request to get UserCurrentLocation : {}", id);
        Optional<UserCurrentLocationDTO> userCurrentLocationDTO = userCurrentLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userCurrentLocationDTO);
    }

    /**
     * {@code DELETE  /user-current-locations/:id} : delete the "id" userCurrentLocation.
     *
     * @param id the id of the userCurrentLocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-current-locations/{id}")
    public ResponseEntity<Void> deleteUserCurrentLocation(@PathVariable String id) {
        log.debug("REST request to delete UserCurrentLocation : {}", id);
        userCurrentLocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
