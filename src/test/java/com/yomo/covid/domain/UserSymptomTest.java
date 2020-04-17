package com.yomo.covid.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yomo.covid.web.rest.TestUtil;

public class UserSymptomTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserSymptom.class);
        UserSymptom userSymptom1 = new UserSymptom();
        userSymptom1.setId("id1");
        UserSymptom userSymptom2 = new UserSymptom();
        userSymptom2.setId(userSymptom1.getId());
        assertThat(userSymptom1).isEqualTo(userSymptom2);
        userSymptom2.setId("id2");
        assertThat(userSymptom1).isNotEqualTo(userSymptom2);
        userSymptom1.setId(null);
        assertThat(userSymptom1).isNotEqualTo(userSymptom2);
    }
}
