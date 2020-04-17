package com.yomo.covid.web.rest;

import com.yomo.covid.service.UserLocationService;
import com.yomo.covid.web.rest.errors.BadRequestAlertException;
import com.yomo.covid.service.dto.UserLocationDTO;

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
 * REST controller for managing {@link com.yomo.covid.domain.UserLocation}.
 */
@RestController
@RequestMapping("/api")
public class UserLocationResource {

    private final Logger log = LoggerFactory.getLogger(UserLocationResource.class);

    private static final String ENTITY_NAME = "userLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserLocationService userLocationService;

    public UserLocationResource(UserLocationService userLocationService) {
        this.userLocationService = userLocationService;
    }

    /**
     * {@code POST  /user-locations} : Create a new userLocation.
     *
     * @param userLocationDTO the userLocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userLocationDTO, or with status {@code 400 (Bad Request)} if the userLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-locations")
    public ResponseEntity<UserLocationDTO> createUserLocation(@RequestBody UserLocationDTO userLocationDTO) throws URISyntaxException {
        log.debug("REST request to save UserLocation : {}", userLocationDTO);
        if (userLocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new userLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserLocationDTO result = userLocationService.save(userLocationDTO);
        return ResponseEntity.created(new URI("/api/user-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-locations} : Updates an existing userLocation.
     *
     * @param userLocationDTO the userLocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userLocationDTO,
     * or with status {@code 400 (Bad Request)} if the userLocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userLocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-locations")
    public ResponseEntity<UserLocationDTO> updateUserLocation(@RequestBody UserLocationDTO userLocationDTO) throws URISyntaxException {
        log.debug("REST request to update UserLocation : {}", userLocationDTO);
        if (userLocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserLocationDTO result = userLocationService.save(userLocationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userLocationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-locations} : get all the userLocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userLocations in body.
     */
    @GetMapping("/user-locations")
    public ResponseEntity<List<UserLocationDTO>> getAllUserLocations(Pageable pageable) {
        log.debug("REST request to get a page of UserLocations");
        Page<UserLocationDTO> page = userLocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-locations/:id} : get the "id" userLocation.
     *
     * @param id the id of the userLocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userLocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-locations/{id}")
    public ResponseEntity<UserLocationDTO> getUserLocation(@PathVariable String id) {
        log.debug("REST request to get UserLocation : {}", id);
        Optional<UserLocationDTO> userLocationDTO = userLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userLocationDTO);
    }

    /**
     * {@code DELETE  /user-locations/:id} : delete the "id" userLocation.
     *
     * @param id the id of the userLocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-locations/{id}")
    public ResponseEntity<Void> deleteUserLocation(@PathVariable String id) {
        log.debug("REST request to delete UserLocation : {}", id);
        userLocationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
