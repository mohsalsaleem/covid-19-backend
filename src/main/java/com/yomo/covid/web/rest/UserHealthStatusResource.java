package com.yomo.covid.web.rest;

import com.yomo.covid.service.UserHealthStatusService;
import com.yomo.covid.web.rest.errors.BadRequestAlertException;
import com.yomo.covid.service.dto.UserHealthStatusDTO;

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
 * REST controller for managing {@link com.yomo.covid.domain.UserHealthStatus}.
 */
@RestController
@RequestMapping("/api")
public class UserHealthStatusResource {

    private final Logger log = LoggerFactory.getLogger(UserHealthStatusResource.class);

    private static final String ENTITY_NAME = "userHealthStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserHealthStatusService userHealthStatusService;

    public UserHealthStatusResource(UserHealthStatusService userHealthStatusService) {
        this.userHealthStatusService = userHealthStatusService;
    }

    /**
     * {@code POST  /user-health-statuses} : Create a new userHealthStatus.
     *
     * @param userHealthStatusDTO the userHealthStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userHealthStatusDTO, or with status {@code 400 (Bad Request)} if the userHealthStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-health-statuses")
    public ResponseEntity<UserHealthStatusDTO> createUserHealthStatus(@RequestBody UserHealthStatusDTO userHealthStatusDTO) throws URISyntaxException {
        log.debug("REST request to save UserHealthStatus : {}", userHealthStatusDTO);
        if (userHealthStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new userHealthStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserHealthStatusDTO result = userHealthStatusService.save(userHealthStatusDTO);
        return ResponseEntity.created(new URI("/api/user-health-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-health-statuses} : Updates an existing userHealthStatus.
     *
     * @param userHealthStatusDTO the userHealthStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userHealthStatusDTO,
     * or with status {@code 400 (Bad Request)} if the userHealthStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userHealthStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-health-statuses")
    public ResponseEntity<UserHealthStatusDTO> updateUserHealthStatus(@RequestBody UserHealthStatusDTO userHealthStatusDTO) throws URISyntaxException {
        log.debug("REST request to update UserHealthStatus : {}", userHealthStatusDTO);
        if (userHealthStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserHealthStatusDTO result = userHealthStatusService.save(userHealthStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userHealthStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-health-statuses} : get all the userHealthStatuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userHealthStatuses in body.
     */
    @GetMapping("/user-health-statuses")
    public ResponseEntity<List<UserHealthStatusDTO>> getAllUserHealthStatuses(Pageable pageable) {
        log.debug("REST request to get a page of UserHealthStatuses");
        Page<UserHealthStatusDTO> page = userHealthStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-health-statuses/:id} : get the "id" userHealthStatus.
     *
     * @param id the id of the userHealthStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userHealthStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-health-statuses/{id}")
    public ResponseEntity<UserHealthStatusDTO> getUserHealthStatus(@PathVariable String id) {
        log.debug("REST request to get UserHealthStatus : {}", id);
        Optional<UserHealthStatusDTO> userHealthStatusDTO = userHealthStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userHealthStatusDTO);
    }

    /**
     * {@code DELETE  /user-health-statuses/:id} : delete the "id" userHealthStatus.
     *
     * @param id the id of the userHealthStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-health-statuses/{id}")
    public ResponseEntity<Void> deleteUserHealthStatus(@PathVariable String id) {
        log.debug("REST request to delete UserHealthStatus : {}", id);
        userHealthStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
