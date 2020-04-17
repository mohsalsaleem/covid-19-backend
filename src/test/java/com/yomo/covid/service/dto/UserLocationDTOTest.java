package com.yomo.covid.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yomo.covid.web.rest.TestUtil;

public class UserLocationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserLocationDTO.class);
        UserLocationDTO userLocationDTO1 = new UserLocationDTO();
        userLocationDTO1.setId("id1");
        UserLocationDTO userLocationDTO2 = new UserLocationDTO();
        assertThat(userLocationDTO1).isNotEqualTo(userLocationDTO2);
        userLocationDTO2.setId(userLocationDTO1.getId());
        assertThat(userLocationDTO1).isEqualTo(userLocationDTO2);
        userLocationDTO2.setId("id2");
        assertThat(userLocationDTO1).isNotEqualTo(userLocationDTO2);
        userLocationDTO1.setId(null);
        assertThat(userLocationDTO1).isNotEqualTo(userLocationDTO2);
    }
}
