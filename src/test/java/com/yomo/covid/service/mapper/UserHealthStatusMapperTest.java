package com.yomo.covid.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserHealthStatusMapperTest {

    private UserHealthStatusMapper userHealthStatusMapper;

    @BeforeEach
    public void setUp() {
        userHealthStatusMapper = new UserHealthStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(userHealthStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userHealthStatusMapper.fromId(null)).isNull();
    }
}
