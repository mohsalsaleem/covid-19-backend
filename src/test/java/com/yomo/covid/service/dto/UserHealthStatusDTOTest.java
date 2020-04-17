package com.yomo.covid.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yomo.covid.web.rest.TestUtil;

public class UserHealthStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserHealthStatusDTO.class);
        UserHealthStatusDTO userHealthStatusDTO1 = new UserHealthStatusDTO();
        userHealthStatusDTO1.setId("id1");
        UserHealthStatusDTO userHealthStatusDTO2 = new UserHealthStatusDTO();
        assertThat(userHealthStatusDTO1).isNotEqualTo(userHealthStatusDTO2);
        userHealthStatusDTO2.setId(userHealthStatusDTO1.getId());
        assertThat(userHealthStatusDTO1).isEqualTo(userHealthStatusDTO2);
        userHealthStatusDTO2.setId("id2");
        assertThat(userHealthStatusDTO1).isNotEqualTo(userHealthStatusDTO2);
        userHealthStatusDTO1.setId(null);
        assertThat(userHealthStatusDTO1).isNotEqualTo(userHealthStatusDTO2);
    }
}
