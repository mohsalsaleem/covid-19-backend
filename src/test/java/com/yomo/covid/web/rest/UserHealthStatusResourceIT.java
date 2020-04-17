package com.yomo.covid.web.rest;

import com.yomo.covid.Covid19App;
import com.yomo.covid.domain.UserHealthStatus;
import com.yomo.covid.repository.UserHealthStatusRepository;
import com.yomo.covid.service.UserHealthStatusService;
import com.yomo.covid.service.dto.UserHealthStatusDTO;
import com.yomo.covid.service.mapper.UserHealthStatusMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.yomo.covid.domain.enumeration.UserHealthSeverity;
/**
 * Integration tests for the {@link UserHealthStatusResource} REST controller.
 */
@SpringBootTest(classes = Covid19App.class)

@AutoConfigureMockMvc
@WithMockUser
public class UserHealthStatusResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final UserHealthSeverity DEFAULT_SEVERITY = UserHealthSeverity.NONE;
    private static final UserHealthSeverity UPDATED_SEVERITY = UserHealthSeverity.LOW;

    @Autowired
    private UserHealthStatusRepository userHealthStatusRepository;

    @Autowired
    private UserHealthStatusMapper userHealthStatusMapper;

    @Autowired
    private UserHealthStatusService userHealthStatusService;

    @Autowired
    private MockMvc restUserHealthStatusMockMvc;

    private UserHealthStatus userHealthStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserHealthStatus createEntity() {
        UserHealthStatus userHealthStatus = new UserHealthStatus()
            .userId(DEFAULT_USER_ID)
            .severity(DEFAULT_SEVERITY);
        return userHealthStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserHealthStatus createUpdatedEntity() {
        UserHealthStatus userHealthStatus = new UserHealthStatus()
            .userId(UPDATED_USER_ID)
            .severity(UPDATED_SEVERITY);
        return userHealthStatus;
    }

    @BeforeEach
    public void initTest() {
        userHealthStatusRepository.deleteAll();
        userHealthStatus = createEntity();
    }

    @Test
    public void createUserHealthStatus() throws Exception {
        int databaseSizeBeforeCreate = userHealthStatusRepository.findAll().size();

        // Create the UserHealthStatus
        UserHealthStatusDTO userHealthStatusDTO = userHealthStatusMapper.toDto(userHealthStatus);
        restUserHealthStatusMockMvc.perform(post("/api/user-health-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userHealthStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the UserHealthStatus in the database
        List<UserHealthStatus> userHealthStatusList = userHealthStatusRepository.findAll();
        assertThat(userHealthStatusList).hasSize(databaseSizeBeforeCreate + 1);
        UserHealthStatus testUserHealthStatus = userHealthStatusList.get(userHealthStatusList.size() - 1);
        assertThat(testUserHealthStatus.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserHealthStatus.getSeverity()).isEqualTo(DEFAULT_SEVERITY);
    }

    @Test
    public void createUserHealthStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userHealthStatusRepository.findAll().size();

        // Create the UserHealthStatus with an existing ID
        userHealthStatus.setId("existing_id");
        UserHealthStatusDTO userHealthStatusDTO = userHealthStatusMapper.toDto(userHealthStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserHealthStatusMockMvc.perform(post("/api/user-health-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userHealthStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserHealthStatus in the database
        List<UserHealthStatus> userHealthStatusList = userHealthStatusRepository.findAll();
        assertThat(userHealthStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllUserHealthStatuses() throws Exception {
        // Initialize the database
        userHealthStatusRepository.save(userHealthStatus);

        // Get all the userHealthStatusList
        restUserHealthStatusMockMvc.perform(get("/api/user-health-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userHealthStatus.getId())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].severity").value(hasItem(DEFAULT_SEVERITY.toString())));
    }
    
    @Test
    public void getUserHealthStatus() throws Exception {
        // Initialize the database
        userHealthStatusRepository.save(userHealthStatus);

        // Get the userHealthStatus
        restUserHealthStatusMockMvc.perform(get("/api/user-health-statuses/{id}", userHealthStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userHealthStatus.getId()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.severity").value(DEFAULT_SEVERITY.toString()));
    }

    @Test
    public void getNonExistingUserHealthStatus() throws Exception {
        // Get the userHealthStatus
        restUserHealthStatusMockMvc.perform(get("/api/user-health-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserHealthStatus() throws Exception {
        // Initialize the database
        userHealthStatusRepository.save(userHealthStatus);

        int databaseSizeBeforeUpdate = userHealthStatusRepository.findAll().size();

        // Update the userHealthStatus
        UserHealthStatus updatedUserHealthStatus = userHealthStatusRepository.findById(userHealthStatus.getId()).get();
        updatedUserHealthStatus
            .userId(UPDATED_USER_ID)
            .severity(UPDATED_SEVERITY);
        UserHealthStatusDTO userHealthStatusDTO = userHealthStatusMapper.toDto(updatedUserHealthStatus);

        restUserHealthStatusMockMvc.perform(put("/api/user-health-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userHealthStatusDTO)))
            .andExpect(status().isOk());

        // Validate the UserHealthStatus in the database
        List<UserHealthStatus> userHealthStatusList = userHealthStatusRepository.findAll();
        assertThat(userHealthStatusList).hasSize(databaseSizeBeforeUpdate);
        UserHealthStatus testUserHealthStatus = userHealthStatusList.get(userHealthStatusList.size() - 1);
        assertThat(testUserHealthStatus.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserHealthStatus.getSeverity()).isEqualTo(UPDATED_SEVERITY);
    }

    @Test
    public void updateNonExistingUserHealthStatus() throws Exception {
        int databaseSizeBeforeUpdate = userHealthStatusRepository.findAll().size();

        // Create the UserHealthStatus
        UserHealthStatusDTO userHealthStatusDTO = userHealthStatusMapper.toDto(userHealthStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserHealthStatusMockMvc.perform(put("/api/user-health-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userHealthStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserHealthStatus in the database
        List<UserHealthStatus> userHealthStatusList = userHealthStatusRepository.findAll();
        assertThat(userHealthStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteUserHealthStatus() throws Exception {
        // Initialize the database
        userHealthStatusRepository.save(userHealthStatus);

        int databaseSizeBeforeDelete = userHealthStatusRepository.findAll().size();

        // Delete the userHealthStatus
        restUserHealthStatusMockMvc.perform(delete("/api/user-health-statuses/{id}", userHealthStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserHealthStatus> userHealthStatusList = userHealthStatusRepository.findAll();
        assertThat(userHealthStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
