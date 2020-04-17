package com.yomo.covid.web.rest;

import com.yomo.covid.Covid19App;
import com.yomo.covid.domain.UserCurrentLocation;
import com.yomo.covid.repository.UserCurrentLocationRepository;
import com.yomo.covid.service.UserCurrentLocationService;
import com.yomo.covid.service.dto.UserCurrentLocationDTO;
import com.yomo.covid.service.mapper.UserCurrentLocationMapper;

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

/**
 * Integration tests for the {@link UserCurrentLocationResource} REST controller.
 */
@SpringBootTest(classes = Covid19App.class)

@AutoConfigureMockMvc
@WithMockUser
public class UserCurrentLocationResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_ID = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_ID = "BBBBBBBBBB";

    @Autowired
    private UserCurrentLocationRepository userCurrentLocationRepository;

    @Autowired
    private UserCurrentLocationMapper userCurrentLocationMapper;

    @Autowired
    private UserCurrentLocationService userCurrentLocationService;

    @Autowired
    private MockMvc restUserCurrentLocationMockMvc;

    private UserCurrentLocation userCurrentLocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserCurrentLocation createEntity() {
        UserCurrentLocation userCurrentLocation = new UserCurrentLocation()
            .userId(DEFAULT_USER_ID)
            .locationId(DEFAULT_LOCATION_ID);
        return userCurrentLocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserCurrentLocation createUpdatedEntity() {
        UserCurrentLocation userCurrentLocation = new UserCurrentLocation()
            .userId(UPDATED_USER_ID)
            .locationId(UPDATED_LOCATION_ID);
        return userCurrentLocation;
    }

    @BeforeEach
    public void initTest() {
        userCurrentLocationRepository.deleteAll();
        userCurrentLocation = createEntity();
    }

    @Test
    public void createUserCurrentLocation() throws Exception {
        int databaseSizeBeforeCreate = userCurrentLocationRepository.findAll().size();

        // Create the UserCurrentLocation
        UserCurrentLocationDTO userCurrentLocationDTO = userCurrentLocationMapper.toDto(userCurrentLocation);
        restUserCurrentLocationMockMvc.perform(post("/api/user-current-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCurrentLocationDTO)))
            .andExpect(status().isCreated());

        // Validate the UserCurrentLocation in the database
        List<UserCurrentLocation> userCurrentLocationList = userCurrentLocationRepository.findAll();
        assertThat(userCurrentLocationList).hasSize(databaseSizeBeforeCreate + 1);
        UserCurrentLocation testUserCurrentLocation = userCurrentLocationList.get(userCurrentLocationList.size() - 1);
        assertThat(testUserCurrentLocation.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserCurrentLocation.getLocationId()).isEqualTo(DEFAULT_LOCATION_ID);
    }

    @Test
    public void createUserCurrentLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userCurrentLocationRepository.findAll().size();

        // Create the UserCurrentLocation with an existing ID
        userCurrentLocation.setId("existing_id");
        UserCurrentLocationDTO userCurrentLocationDTO = userCurrentLocationMapper.toDto(userCurrentLocation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserCurrentLocationMockMvc.perform(post("/api/user-current-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCurrentLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserCurrentLocation in the database
        List<UserCurrentLocation> userCurrentLocationList = userCurrentLocationRepository.findAll();
        assertThat(userCurrentLocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCurrentLocationRepository.findAll().size();
        // set the field null
        userCurrentLocation.setUserId(null);

        // Create the UserCurrentLocation, which fails.
        UserCurrentLocationDTO userCurrentLocationDTO = userCurrentLocationMapper.toDto(userCurrentLocation);

        restUserCurrentLocationMockMvc.perform(post("/api/user-current-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCurrentLocationDTO)))
            .andExpect(status().isBadRequest());

        List<UserCurrentLocation> userCurrentLocationList = userCurrentLocationRepository.findAll();
        assertThat(userCurrentLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLocationIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = userCurrentLocationRepository.findAll().size();
        // set the field null
        userCurrentLocation.setLocationId(null);

        // Create the UserCurrentLocation, which fails.
        UserCurrentLocationDTO userCurrentLocationDTO = userCurrentLocationMapper.toDto(userCurrentLocation);

        restUserCurrentLocationMockMvc.perform(post("/api/user-current-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCurrentLocationDTO)))
            .andExpect(status().isBadRequest());

        List<UserCurrentLocation> userCurrentLocationList = userCurrentLocationRepository.findAll();
        assertThat(userCurrentLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllUserCurrentLocations() throws Exception {
        // Initialize the database
        userCurrentLocationRepository.save(userCurrentLocation);

        // Get all the userCurrentLocationList
        restUserCurrentLocationMockMvc.perform(get("/api/user-current-locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userCurrentLocation.getId())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].locationId").value(hasItem(DEFAULT_LOCATION_ID)));
    }
    
    @Test
    public void getUserCurrentLocation() throws Exception {
        // Initialize the database
        userCurrentLocationRepository.save(userCurrentLocation);

        // Get the userCurrentLocation
        restUserCurrentLocationMockMvc.perform(get("/api/user-current-locations/{id}", userCurrentLocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userCurrentLocation.getId()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.locationId").value(DEFAULT_LOCATION_ID));
    }

    @Test
    public void getNonExistingUserCurrentLocation() throws Exception {
        // Get the userCurrentLocation
        restUserCurrentLocationMockMvc.perform(get("/api/user-current-locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserCurrentLocation() throws Exception {
        // Initialize the database
        userCurrentLocationRepository.save(userCurrentLocation);

        int databaseSizeBeforeUpdate = userCurrentLocationRepository.findAll().size();

        // Update the userCurrentLocation
        UserCurrentLocation updatedUserCurrentLocation = userCurrentLocationRepository.findById(userCurrentLocation.getId()).get();
        updatedUserCurrentLocation
            .userId(UPDATED_USER_ID)
            .locationId(UPDATED_LOCATION_ID);
        UserCurrentLocationDTO userCurrentLocationDTO = userCurrentLocationMapper.toDto(updatedUserCurrentLocation);

        restUserCurrentLocationMockMvc.perform(put("/api/user-current-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCurrentLocationDTO)))
            .andExpect(status().isOk());

        // Validate the UserCurrentLocation in the database
        List<UserCurrentLocation> userCurrentLocationList = userCurrentLocationRepository.findAll();
        assertThat(userCurrentLocationList).hasSize(databaseSizeBeforeUpdate);
        UserCurrentLocation testUserCurrentLocation = userCurrentLocationList.get(userCurrentLocationList.size() - 1);
        assertThat(testUserCurrentLocation.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserCurrentLocation.getLocationId()).isEqualTo(UPDATED_LOCATION_ID);
    }

    @Test
    public void updateNonExistingUserCurrentLocation() throws Exception {
        int databaseSizeBeforeUpdate = userCurrentLocationRepository.findAll().size();

        // Create the UserCurrentLocation
        UserCurrentLocationDTO userCurrentLocationDTO = userCurrentLocationMapper.toDto(userCurrentLocation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserCurrentLocationMockMvc.perform(put("/api/user-current-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userCurrentLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserCurrentLocation in the database
        List<UserCurrentLocation> userCurrentLocationList = userCurrentLocationRepository.findAll();
        assertThat(userCurrentLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteUserCurrentLocation() throws Exception {
        // Initialize the database
        userCurrentLocationRepository.save(userCurrentLocation);

        int databaseSizeBeforeDelete = userCurrentLocationRepository.findAll().size();

        // Delete the userCurrentLocation
        restUserCurrentLocationMockMvc.perform(delete("/api/user-current-locations/{id}", userCurrentLocation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserCurrentLocation> userCurrentLocationList = userCurrentLocationRepository.findAll();
        assertThat(userCurrentLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
