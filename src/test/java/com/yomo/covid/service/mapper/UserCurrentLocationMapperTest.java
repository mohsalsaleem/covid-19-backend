package com.yomo.covid.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserCurrentLocationMapperTest {

    private UserCurrentLocationMapper userCurrentLocationMapper;

    @BeforeEach
    public void setUp() {
        userCurrentLocationMapper = new UserCurrentLocationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(userCurrentLocationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userCurrentLocationMapper.fromId(null)).isNull();
    }
}
