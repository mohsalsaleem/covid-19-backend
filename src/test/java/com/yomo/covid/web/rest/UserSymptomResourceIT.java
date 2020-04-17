package com.yomo.covid.web.rest;

import com.yomo.covid.Covid19App;
import com.yomo.covid.domain.UserSymptom;
import com.yomo.covid.repository.UserSymptomRepository;
import com.yomo.covid.service.UserSymptomService;
import com.yomo.covid.service.dto.UserSymptomDTO;
import com.yomo.covid.service.mapper.UserSymptomMapper;

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
 * Integration tests for the {@link UserSymptomResource} REST controller.
 */
@SpringBootTest(classes = Covid19App.class)

@AutoConfigureMockMvc
@WithMockUser
public class UserSymptomResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SYMPTOM = "AAAAAAAAAA";
    private static final String UPDATED_SYMPTOM = "BBBBBBBBBB";

    @Autowired
    private UserSymptomRepository userSymptomRepository;

    @Autowired
    private UserSymptomMapper userSymptomMapper;

    @Autowired
    private UserSymptomService userSymptomService;

    @Autowired
    private MockMvc restUserSymptomMockMvc;

    private UserSymptom userSymptom;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserSymptom createEntity() {
        UserSymptom userSymptom = new UserSymptom()
            .userId(DEFAULT_USER_ID)
            .symptom(DEFAULT_SYMPTOM);
        return userSymptom;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserSymptom createUpdatedEntity() {
        UserSymptom userSymptom = new UserSymptom()
            .userId(UPDATED_USER_ID)
            .symptom(UPDATED_SYMPTOM);
        return userSymptom;
    }

    @BeforeEach
    public void initTest() {
        userSymptomRepository.deleteAll();
        userSymptom = createEntity();
    }

    @Test
    public void createUserSymptom() throws Exception {
        int databaseSizeBeforeCreate = userSymptomRepository.findAll().size();

        // Create the UserSymptom
        UserSymptomDTO userSymptomDTO = userSymptomMapper.toDto(userSymptom);
        restUserSymptomMockMvc.perform(post("/api/user-symptoms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userSymptomDTO)))
            .andExpect(status().isCreated());

        // Validate the UserSymptom in the database
        List<UserSymptom> userSymptomList = userSymptomRepository.findAll();
        assertThat(userSymptomList).hasSize(databaseSizeBeforeCreate + 1);
        UserSymptom testUserSymptom = userSymptomList.get(userSymptomList.size() - 1);
        assertThat(testUserSymptom.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserSymptom.getSymptom()).isEqualTo(DEFAULT_SYMPTOM);
    }

    @Test
    public void createUserSymptomWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userSymptomRepository.findAll().size();

        // Create the UserSymptom with an existing ID
        userSymptom.setId("existing_id");
        UserSymptomDTO userSymptomDTO = userSymptomMapper.toDto(userSymptom);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserSymptomMockMvc.perform(post("/api/user-symptoms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userSymptomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserSymptom in the database
        List<UserSymptom> userSymptomList = userSymptomRepository.findAll();
        assertThat(userSymptomList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = userSymptomRepository.findAll().size();
        // set the field null
        userSymptom.setUserId(null);

        // Create the UserSymptom, which fails.
        UserSymptomDTO userSymptomDTO = userSymptomMapper.toDto(userSymptom);

        restUserSymptomMockMvc.perform(post("/api/user-symptoms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userSymptomDTO)))
            .andExpect(status().isBadRequest());

        List<UserSymptom> userSymptomList = userSymptomRepository.findAll();
        assertThat(userSymptomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSymptomIsRequired() throws Exception {
        int databaseSizeBeforeTest = userSymptomRepository.findAll().size();
        // set the field null
        userSymptom.setSymptom(null);

        // Create the UserSymptom, which fails.
        UserSymptomDTO userSymptomDTO = userSymptomMapper.toDto(userSymptom);

        restUserSymptomMockMvc.perform(post("/api/user-symptoms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userSymptomDTO)))
            .andExpect(status().isBadRequest());

        List<UserSymptom> userSymptomList = userSymptomRepository.findAll();
        assertThat(userSymptomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllUserSymptoms() throws Exception {
        // Initialize the database
        userSymptomRepository.save(userSymptom);

        // Get all the userSymptomList
        restUserSymptomMockMvc.perform(get("/api/user-symptoms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userSymptom.getId())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].symptom").value(hasItem(DEFAULT_SYMPTOM)));
    }
    
    @Test
    public void getUserSymptom() throws Exception {
        // Initialize the database
        userSymptomRepository.save(userSymptom);

        // Get the userSymptom
        restUserSymptomMockMvc.perform(get("/api/user-symptoms/{id}", userSymptom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userSymptom.getId()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.symptom").value(DEFAULT_SYMPTOM));
    }

    @Test
    public void getNonExistingUserSymptom() throws Exception {
        // Get the userSymptom
        restUserSymptomMockMvc.perform(get("/api/user-symptoms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserSymptom() throws Exception {
        // Initialize the database
        userSymptomRepository.save(userSymptom);

        int databaseSizeBeforeUpdate = userSymptomRepository.findAll().size();

        // Update the userSymptom
        UserSymptom updatedUserSymptom = userSymptomRepository.findById(userSymptom.getId()).get();
        updatedUserSymptom
            .userId(UPDATED_USER_ID)
            .symptom(UPDATED_SYMPTOM);
        UserSymptomDTO userSymptomDTO = userSymptomMapper.toDto(updatedUserSymptom);

        restUserSymptomMockMvc.perform(put("/api/user-symptoms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userSymptomDTO)))
            .andExpect(status().isOk());

        // Validate the UserSymptom in the database
        List<UserSymptom> userSymptomList = userSymptomRepository.findAll();
        assertThat(userSymptomList).hasSize(databaseSizeBeforeUpdate);
        UserSymptom testUserSymptom = userSymptomList.get(userSymptomList.size() - 1);
        assertThat(testUserSymptom.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserSymptom.getSymptom()).isEqualTo(UPDATED_SYMPTOM);
    }

    @Test
    public void updateNonExistingUserSymptom() throws Exception {
        int databaseSizeBeforeUpdate = userSymptomRepository.findAll().size();

        // Create the UserSymptom
        UserSymptomDTO userSymptomDTO = userSymptomMapper.toDto(userSymptom);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserSymptomMockMvc.perform(put("/api/user-symptoms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userSymptomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserSymptom in the database
        List<UserSymptom> userSymptomList = userSymptomRepository.findAll();
        assertThat(userSymptomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteUserSymptom() throws Exception {
        // Initialize the database
        userSymptomRepository.save(userSymptom);

        int databaseSizeBeforeDelete = userSymptomRepository.findAll().size();

        // Delete the userSymptom
        restUserSymptomMockMvc.perform(delete("/api/user-symptoms/{id}", userSymptom.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserSymptom> userSymptomList = userSymptomRepository.findAll();
        assertThat(userSymptomList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
