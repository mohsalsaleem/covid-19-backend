package com.yomo.covid.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yomo.covid.web.rest.TestUtil;

public class UserCurrentLocationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCurrentLocationDTO.class);
        UserCurrentLocationDTO userCurrentLocationDTO1 = new UserCurrentLocationDTO();
        userCurrentLocationDTO1.setId("id1");
        UserCurrentLocationDTO userCurrentLocationDTO2 = new UserCurrentLocationDTO();
        assertThat(userCurrentLocationDTO1).isNotEqualTo(userCurrentLocationDTO2);
        userCurrentLocationDTO2.setId(userCurrentLocationDTO1.getId());
        assertThat(userCurrentLocationDTO1).isEqualTo(userCurrentLocationDTO2);
        userCurrentLocationDTO2.setId("id2");
        assertThat(userCurrentLocationDTO1).isNotEqualTo(userCurrentLocationDTO2);
        userCurrentLocationDTO1.setId(null);
        assertThat(userCurrentLocationDTO1).isNotEqualTo(userCurrentLocationDTO2);
    }
}
