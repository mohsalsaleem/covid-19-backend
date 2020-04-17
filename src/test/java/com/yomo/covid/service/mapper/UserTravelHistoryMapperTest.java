package com.yomo.covid.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTravelHistoryMapperTest {

    private UserTravelHistoryMapper userTravelHistoryMapper;

    @BeforeEach
    public void setUp() {
        userTravelHistoryMapper = new UserTravelHistoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        String id = "id1";
        assertThat(userTravelHistoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userTravelHistoryMapper.fromId(null)).isNull();
    }
}
