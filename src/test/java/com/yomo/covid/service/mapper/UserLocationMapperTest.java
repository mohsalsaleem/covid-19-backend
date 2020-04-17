package com.yomo.covid.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserLocationMapperTest {

    private UserLocationMapper userLocationMapper;

    @BeforeEach
    public void setUp() {
        userLocationMapper = new UserLocationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(userLocationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userLocationMapper.fromId(null)).isNull();
    }
}
