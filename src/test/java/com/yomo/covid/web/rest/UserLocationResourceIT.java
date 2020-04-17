package com.yomo.covid.web.rest;

import com.yomo.covid.Covid19App;
import com.yomo.covid.domain.UserLocation;
import com.yomo.covid.repository.UserLocationRepository;
import com.yomo.covid.service.UserLocationService;
import com.yomo.covid.service.dto.UserLocationDTO;
import com.yomo.covid.service.mapper.UserLocationMapper;

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
 * Integration tests for the {@link UserLocationResource} REST controller.
 */
@SpringBootTest(classes = Covid19App.class)

@AutoConfigureMockMvc
@WithMockUser
public class UserLocationResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_LATITUDE = 1L;
    private static final Long UPDATED_LATITUDE = 2L;

    private static final Long DEFAULT_LONGITUDE = 1L;
    private static final Long UPDATED_LONGITUDE = 2L;

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    @Autowired
    private UserLocationRepository userLocationRepository;

    @Autowired
    private UserLocationMapper userLocationMapper;

    @Autowired
    private UserLocationService userLocationService;

    @Autowired
    private MockMvc restUserLocationMockMvc;

    private UserLocation userLocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserLocation createEntity() {
        UserLocation userLocation = new UserLocation()
            .userId(DEFAULT_USER_ID)
            .locationName(DEFAULT_LOCATION_NAME);
        return userLocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserLocation createUpdatedEntity() {
        UserLocation userLocation = new UserLocation()
            .userId(UPDATED_USER_ID)
            .locationName(UPDATED_LOCATION_NAME);
        return userLocation;
    }

    @BeforeEach
    public void initTest() {
        userLocationRepository.deleteAll();
        userLocation = createEntity();
    }

    @Test
    public void createUserLocation() throws Exception {
        int databaseSizeBeforeCreate = userLocationRepository.findAll().size();

        // Create the UserLocation
        UserLocationDTO userLocationDTO = userLocationMapper.toDto(userLocation);
        restUserLocationMockMvc.perform(post("/api/user-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userLocationDTO)))
            .andExpect(status().isCreated());

        // Validate the UserLocation in the database
        List<UserLocation> userLocationList = userLocationRepository.findAll();
        assertThat(userLocationList).hasSize(databaseSizeBeforeCreate + 1);
        UserLocation testUserLocation = userLocationList.get(userLocationList.size() - 1);
        assertThat(testUserLocation.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserLocation.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
    }

    @Test
    public void createUserLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userLocationRepository.findAll().size();

        // Create the UserLocation with an existing ID
        userLocation.setId("existing_id");
        UserLocationDTO userLocationDTO = userLocationMapper.toDto(userLocation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserLocationMockMvc.perform(post("/api/user-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserLocation in the database
        List<UserLocation> userLocationList = userLocationRepository.findAll();
        assertThat(userLocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = userLocationRepository.findAll().size();
        // set the field null
        userLocation.setUserId(null);

        // Create the UserLocation, which fails.
        UserLocationDTO userLocationDTO = userLocationMapper.toDto(userLocation);

        restUserLocationMockMvc.perform(post("/api/user-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userLocationDTO)))
            .andExpect(status().isBadRequest());

        List<UserLocation> userLocationList = userLocationRepository.findAll();
        assertThat(userLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = userLocationRepository.findAll().size();
        // set the field null

        // Create the UserLocation, which fails.
        UserLocationDTO userLocationDTO = userLocationMapper.toDto(userLocation);

        restUserLocationMockMvc.perform(post("/api/user-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userLocationDTO)))
            .andExpect(status().isBadRequest());

        List<UserLocation> userLocationList = userLocationRepository.findAll();
        assertThat(userLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = userLocationRepository.findAll().size();
        // set the field null

        // Create the UserLocation, which fails.
        UserLocationDTO userLocationDTO = userLocationMapper.toDto(userLocation);

        restUserLocationMockMvc.perform(post("/api/user-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userLocationDTO)))
            .andExpect(status().isBadRequest());

        List<UserLocation> userLocationList = userLocationRepository.findAll();
        assertThat(userLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLocationNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userLocationRepository.findAll().size();
        // set the field null
        userLocation.setLocationName(null);

        // Create the UserLocation, which fails.
        UserLocationDTO userLocationDTO = userLocationMapper.toDto(userLocation);

        restUserLocationMockMvc.perform(post("/api/user-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userLocationDTO)))
            .andExpect(status().isBadRequest());

        List<UserLocation> userLocationList = userLocationRepository.findAll();
        assertThat(userLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllUserLocations() throws Exception {
        // Initialize the database
        userLocationRepository.save(userLocation);

        // Get all the userLocationList
        restUserLocationMockMvc.perform(get("/api/user-locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userLocation.getId())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.intValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.intValue())))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)));
    }

    @Test
    public void getUserLocation() throws Exception {
        // Initialize the database
        userLocationRepository.save(userLocation);

        // Get the userLocation
        restUserLocationMockMvc.perform(get("/api/user-locations/{id}", userLocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userLocation.getId()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.intValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.intValue()))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME));
    }

    @Test
    public void getNonExistingUserLocation() throws Exception {
        // Get the userLocation
        restUserLocationMockMvc.perform(get("/api/user-locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserLocation() throws Exception {
        // Initialize the database
        userLocationRepository.save(userLocation);

        int databaseSizeBeforeUpdate = userLocationRepository.findAll().size();

        // Update the userLocation
        UserLocation updatedUserLocation = userLocationRepository.findById(userLocation.getId()).get();
        updatedUserLocation
            .userId(UPDATED_USER_ID)
            .locationName(UPDATED_LOCATION_NAME);
        UserLocationDTO userLocationDTO = userLocationMapper.toDto(updatedUserLocation);

        restUserLocationMockMvc.perform(put("/api/user-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userLocationDTO)))
            .andExpect(status().isOk());

        // Validate the UserLocation in the database
        List<UserLocation> userLocationList = userLocationRepository.findAll();
        assertThat(userLocationList).hasSize(databaseSizeBeforeUpdate);
        UserLocation testUserLocation = userLocationList.get(userLocationList.size() - 1);
        assertThat(testUserLocation.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserLocation.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
    }

    @Test
    public void updateNonExistingUserLocation() throws Exception {
        int databaseSizeBeforeUpdate = userLocationRepository.findAll().size();

        // Create the UserLocation
        UserLocationDTO userLocationDTO = userLocationMapper.toDto(userLocation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserLocationMockMvc.perform(put("/api/user-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserLocation in the database
        List<UserLocation> userLocationList = userLocationRepository.findAll();
        assertThat(userLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteUserLocation() throws Exception {
        // Initialize the database
        userLocationRepository.save(userLocation);

        int databaseSizeBeforeDelete = userLocationRepository.findAll().size();

        // Delete the userLocation
        restUserLocationMockMvc.perform(delete("/api/user-locations/{id}", userLocation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserLocation> userLocationList = userLocationRepository.findAll();
        assertThat(userLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
