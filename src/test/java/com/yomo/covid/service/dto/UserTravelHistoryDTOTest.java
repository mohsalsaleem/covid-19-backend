package com.yomo.covid.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yomo.covid.web.rest.TestUtil;

public class UserTravelHistoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserTravelHistoryDTO.class);
        UserTravelHistoryDTO userTravelHistoryDTO1 = new UserTravelHistoryDTO();
        userTravelHistoryDTO1.setId("id1");
        UserTravelHistoryDTO userTravelHistoryDTO2 = new UserTravelHistoryDTO();
        assertThat(userTravelHistoryDTO1).isNotEqualTo(userTravelHistoryDTO2);
        userTravelHistoryDTO2.setId(userTravelHistoryDTO1.getId());
        assertThat(userTravelHistoryDTO1).isEqualTo(userTravelHistoryDTO2);
        userTravelHistoryDTO2.setId("id2");
        assertThat(userTravelHistoryDTO1).isNotEqualTo(userTravelHistoryDTO2);
        userTravelHistoryDTO1.setId(null);
        assertThat(userTravelHistoryDTO1).isNotEqualTo(userTravelHistoryDTO2);
    }
}
