package com.yomo.covid.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yomo.covid.web.rest.TestUtil;

public class UserSymptomDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserSymptomDTO.class);
        UserSymptomDTO userSymptomDTO1 = new UserSymptomDTO();
        userSymptomDTO1.setId("id1");
        UserSymptomDTO userSymptomDTO2 = new UserSymptomDTO();
        assertThat(userSymptomDTO1).isNotEqualTo(userSymptomDTO2);
        userSymptomDTO2.setId(userSymptomDTO1.getId());
        assertThat(userSymptomDTO1).isEqualTo(userSymptomDTO2);
        userSymptomDTO2.setId("id2");
        assertThat(userSymptomDTO1).isNotEqualTo(userSymptomDTO2);
        userSymptomDTO1.setId(null);
        assertThat(userSymptomDTO1).isNotEqualTo(userSymptomDTO2);
    }
}
