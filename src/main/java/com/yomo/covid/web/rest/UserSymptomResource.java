package com.yomo.covid.web.rest;

import com.yomo.covid.service.UserSymptomService;
import com.yomo.covid.web.rest.errors.BadRequestAlertException;
import com.yomo.covid.service.dto.UserSymptomDTO;

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
 * REST controller for managing {@link com.yomo.covid.domain.UserSymptom}.
 */
@RestController
@RequestMapping("/api")
public class UserSymptomResource {

    private final Logger log = LoggerFactory.getLogger(UserSymptomResource.class);

    private static final String ENTITY_NAME = "userSymptom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserSymptomService userSymptomService;

    public UserSymptomResource(UserSymptomService userSymptomService) {
        this.userSymptomService = userSymptomService;
    }

    /**
     * {@code POST  /user-symptoms} : Create a new userSymptom.
     *
     * @param userSymptomDTO the userSymptomDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userSymptomDTO, or with status {@code 400 (Bad Request)} if the userSymptom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-symptoms")
    public ResponseEntity<UserSymptomDTO> createUserSymptom(@RequestBody UserSymptomDTO userSymptomDTO) throws URISyntaxException {
        log.debug("REST request to save UserSymptom : {}", userSymptomDTO);
        if (userSymptomDTO.getId() != null) {
            throw new BadRequestAlertException("A new userSymptom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserSymptomDTO result = userSymptomService.save(userSymptomDTO);
        return ResponseEntity.created(new URI("/api/user-symptoms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-symptoms} : Updates an existing userSymptom.
     *
     * @param userSymptomDTO the userSymptomDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userSymptomDTO,
     * or with status {@code 400 (Bad Request)} if the userSymptomDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userSymptomDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-symptoms")
    public ResponseEntity<UserSymptomDTO> updateUserSymptom(@RequestBody UserSymptomDTO userSymptomDTO) throws URISyntaxException {
        log.debug("REST request to update UserSymptom : {}", userSymptomDTO);
        if (userSymptomDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserSymptomDTO result = userSymptomService.save(userSymptomDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userSymptomDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-symptoms} : get all the userSymptoms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userSymptoms in body.
     */
    @GetMapping("/user-symptoms")
    public ResponseEntity<List<UserSymptomDTO>> getAllUserSymptoms(Pageable pageable) {
        log.debug("REST request to get a page of UserSymptoms");
        Page<UserSymptomDTO> page = userSymptomService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-symptoms/:id} : get the "id" userSymptom.
     *
     * @param id the id of the userSymptomDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userSymptomDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-symptoms/{id}")
    public ResponseEntity<UserSymptomDTO> getUserSymptom(@PathVariable String id) {
        log.debug("REST request to get UserSymptom : {}", id);
        Optional<UserSymptomDTO> userSymptomDTO = userSymptomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userSymptomDTO);
    }

    /**
     * {@code DELETE  /user-symptoms/:id} : delete the "id" userSymptom.
     *
     * @param id the id of the userSymptomDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-symptoms/{id}")
    public ResponseEntity<Void> deleteUserSymptom(@PathVariable String id) {
        log.debug("REST request to delete UserSymptom : {}", id);
        userSymptomService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
