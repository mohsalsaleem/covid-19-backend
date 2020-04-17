package com.yomo.covid.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserSymptomMapperTest {

    private UserSymptomMapper userSymptomMapper;

    @BeforeEach
    public void setUp() {
        userSymptomMapper = new UserSymptomMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(userSymptomMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userSymptomMapper.fromId(null)).isNull();
    }
}
