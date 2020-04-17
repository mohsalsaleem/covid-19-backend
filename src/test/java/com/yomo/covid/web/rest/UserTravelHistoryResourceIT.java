package com.yomo.covid.web.rest;

import com.yomo.covid.Covid19App;
import com.yomo.covid.domain.UserTravelHistory;
import com.yomo.covid.repository.UserTravelHistoryRepository;
import com.yomo.covid.service.UserTravelHistoryService;
import com.yomo.covid.service.dto.UserTravelHistoryDTO;
import com.yomo.covid.service.mapper.UserTravelHistoryMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserTravelHistoryResource} REST controller.
 */
@SpringBootTest(classes = Covid19App.class)

@AutoConfigureMockMvc
@WithMockUser
public class UserTravelHistoryResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_LATITUDE = 1L;
    private static final Long UPDATED_LATITUDE = 2L;

    private static final Long DEFAULT_LONGITUDE = 1L;
    private static final Long UPDATED_LONGITUDE = 2L;

    private static final Instant DEFAULT_DATE_AND_TIME_OF_TRAVEL = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_AND_TIME_OF_TRAVEL = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private UserTravelHistoryRepository userTravelHistoryRepository;

    @Autowired
    private UserTravelHistoryMapper userTravelHistoryMapper;

    @Autowired
    private UserTravelHistoryService userTravelHistoryService;

    @Autowired
    private MockMvc restUserTravelHistoryMockMvc;

    private UserTravelHistory userTravelHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserTravelHistory createEntity() {
        UserTravelHistory userTravelHistory = new UserTravelHistory()
            .userId(DEFAULT_USER_ID)
            .locationName(DEFAULT_LOCATION_NAME)
            .dateAndTimeOfTravel(DEFAULT_DATE_AND_TIME_OF_TRAVEL);
        return userTravelHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserTravelHistory createUpdatedEntity() {
        UserTravelHistory userTravelHistory = new UserTravelHistory()
            .userId(UPDATED_USER_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .dateAndTimeOfTravel(UPDATED_DATE_AND_TIME_OF_TRAVEL);
        return userTravelHistory;
    }

    @BeforeEach
    public void initTest() {
        userTravelHistoryRepository.deleteAll();
        userTravelHistory = createEntity();
    }

    @Test
    public void createUserTravelHistory() throws Exception {
        int databaseSizeBeforeCreate = userTravelHistoryRepository.findAll().size();

        // Create the UserTravelHistory
        UserTravelHistoryDTO userTravelHistoryDTO = userTravelHistoryMapper.toDto(userTravelHistory);
        restUserTravelHistoryMockMvc.perform(post("/api/user-travel-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userTravelHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the UserTravelHistory in the database
        List<UserTravelHistory> userTravelHistoryList = userTravelHistoryRepository.findAll();
        assertThat(userTravelHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        UserTravelHistory testUserTravelHistory = userTravelHistoryList.get(userTravelHistoryList.size() - 1);
        assertThat(testUserTravelHistory.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserTravelHistory.getLocationName()).isEqualTo(DEFAULT_LOCATION_NAME);
        assertThat(testUserTravelHistory.getDateAndTimeOfTravel()).isEqualTo(DEFAULT_DATE_AND_TIME_OF_TRAVEL);
    }

    @Test
    public void createUserTravelHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userTravelHistoryRepository.findAll().size();

        // Create the UserTravelHistory with an existing ID
        userTravelHistory.setId("existing_id");
        UserTravelHistoryDTO userTravelHistoryDTO = userTravelHistoryMapper.toDto(userTravelHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserTravelHistoryMockMvc.perform(post("/api/user-travel-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userTravelHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserTravelHistory in the database
        List<UserTravelHistory> userTravelHistoryList = userTravelHistoryRepository.findAll();
        assertThat(userTravelHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = userTravelHistoryRepository.findAll().size();
        // set the field null
        userTravelHistory.setUserId(null);

        // Create the UserTravelHistory, which fails.
        UserTravelHistoryDTO userTravelHistoryDTO = userTravelHistoryMapper.toDto(userTravelHistory);

        restUserTravelHistoryMockMvc.perform(post("/api/user-travel-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userTravelHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<UserTravelHistory> userTravelHistoryList = userTravelHistoryRepository.findAll();
        assertThat(userTravelHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLocationNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userTravelHistoryRepository.findAll().size();
        // set the field null
        userTravelHistory.setLocationName(null);

        // Create the UserTravelHistory, which fails.
        UserTravelHistoryDTO userTravelHistoryDTO = userTravelHistoryMapper.toDto(userTravelHistory);

        restUserTravelHistoryMockMvc.perform(post("/api/user-travel-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userTravelHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<UserTravelHistory> userTravelHistoryList = userTravelHistoryRepository.findAll();
        assertThat(userTravelHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = userTravelHistoryRepository.findAll().size();
        // set the field null

        // Create the UserTravelHistory, which fails.
        UserTravelHistoryDTO userTravelHistoryDTO = userTravelHistoryMapper.toDto(userTravelHistory);

        restUserTravelHistoryMockMvc.perform(post("/api/user-travel-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userTravelHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<UserTravelHistory> userTravelHistoryList = userTravelHistoryRepository.findAll();
        assertThat(userTravelHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = userTravelHistoryRepository.findAll().size();
        // set the field null

        // Create the UserTravelHistory, which fails.
        UserTravelHistoryDTO userTravelHistoryDTO = userTravelHistoryMapper.toDto(userTravelHistory);

        restUserTravelHistoryMockMvc.perform(post("/api/user-travel-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userTravelHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<UserTravelHistory> userTravelHistoryList = userTravelHistoryRepository.findAll();
        assertThat(userTravelHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDateAndTimeOfTravelIsRequired() throws Exception {
        int databaseSizeBeforeTest = userTravelHistoryRepository.findAll().size();
        // set the field null
        userTravelHistory.setDateAndTimeOfTravel(null);

        // Create the UserTravelHistory, which fails.
        UserTravelHistoryDTO userTravelHistoryDTO = userTravelHistoryMapper.toDto(userTravelHistory);

        restUserTravelHistoryMockMvc.perform(post("/api/user-travel-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userTravelHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<UserTravelHistory> userTravelHistoryList = userTravelHistoryRepository.findAll();
        assertThat(userTravelHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllUserTravelHistories() throws Exception {
        // Initialize the database
        userTravelHistoryRepository.save(userTravelHistory);

        // Get all the userTravelHistoryList
        restUserTravelHistoryMockMvc.perform(get("/api/user-travel-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userTravelHistory.getId())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].locationName").value(hasItem(DEFAULT_LOCATION_NAME)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.intValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.intValue())))
            .andExpect(jsonPath("$.[*].dateAndTimeOfTravel").value(hasItem(DEFAULT_DATE_AND_TIME_OF_TRAVEL.toString())));
    }

    @Test
    public void getUserTravelHistory() throws Exception {
        // Initialize the database
        userTravelHistoryRepository.save(userTravelHistory);

        // Get the userTravelHistory
        restUserTravelHistoryMockMvc.perform(get("/api/user-travel-histories/{id}", userTravelHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userTravelHistory.getId()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.locationName").value(DEFAULT_LOCATION_NAME))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.intValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.intValue()))
            .andExpect(jsonPath("$.dateAndTimeOfTravel").value(DEFAULT_DATE_AND_TIME_OF_TRAVEL.toString()));
    }

    @Test
    public void getNonExistingUserTravelHistory() throws Exception {
        // Get the userTravelHistory
        restUserTravelHistoryMockMvc.perform(get("/api/user-travel-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserTravelHistory() throws Exception {
        // Initialize the database
        userTravelHistoryRepository.save(userTravelHistory);

        int databaseSizeBeforeUpdate = userTravelHistoryRepository.findAll().size();

        // Update the userTravelHistory
        UserTravelHistory updatedUserTravelHistory = userTravelHistoryRepository.findById(userTravelHistory.getId()).get();
        updatedUserTravelHistory
            .userId(UPDATED_USER_ID)
            .locationName(UPDATED_LOCATION_NAME)
            .dateAndTimeOfTravel(UPDATED_DATE_AND_TIME_OF_TRAVEL);
        UserTravelHistoryDTO userTravelHistoryDTO = userTravelHistoryMapper.toDto(updatedUserTravelHistory);

        restUserTravelHistoryMockMvc.perform(put("/api/user-travel-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userTravelHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the UserTravelHistory in the database
        List<UserTravelHistory> userTravelHistoryList = userTravelHistoryRepository.findAll();
        assertThat(userTravelHistoryList).hasSize(databaseSizeBeforeUpdate);
        UserTravelHistory testUserTravelHistory = userTravelHistoryList.get(userTravelHistoryList.size() - 1);
        assertThat(testUserTravelHistory.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserTravelHistory.getLocationName()).isEqualTo(UPDATED_LOCATION_NAME);
        assertThat(testUserTravelHistory.getDateAndTimeOfTravel()).isEqualTo(UPDATED_DATE_AND_TIME_OF_TRAVEL);
    }

    @Test
    public void updateNonExistingUserTravelHistory() throws Exception {
        int databaseSizeBeforeUpdate = userTravelHistoryRepository.findAll().size();

        // Create the UserTravelHistory
        UserTravelHistoryDTO userTravelHistoryDTO = userTravelHistoryMapper.toDto(userTravelHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserTravelHistoryMockMvc.perform(put("/api/user-travel-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userTravelHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserTravelHistory in the database
        List<UserTravelHistory> userTravelHistoryList = userTravelHistoryRepository.findAll();
        assertThat(userTravelHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteUserTravelHistory() throws Exception {
        // Initialize the database
        userTravelHistoryRepository.save(userTravelHistory);

        int databaseSizeBeforeDelete = userTravelHistoryRepository.findAll().size();

        // Delete the userTravelHistory
        restUserTravelHistoryMockMvc.perform(delete("/api/user-travel-histories/{id}", userTravelHistory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserTravelHistory> userTravelHistoryList = userTravelHistoryRepository.findAll();
        assertThat(userTravelHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
