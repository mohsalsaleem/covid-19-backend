package com.yomo.covid.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yomo.covid.web.rest.TestUtil;

public class UserHealthStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserHealthStatus.class);
        UserHealthStatus userHealthStatus1 = new UserHealthStatus();
        userHealthStatus1.setId("id1");
        UserHealthStatus userHealthStatus2 = new UserHealthStatus();
        userHealthStatus2.setId(userHealthStatus1.getId());
        assertThat(userHealthStatus1).isEqualTo(userHealthStatus2);
        userHealthStatus2.setId("id2");
        assertThat(userHealthStatus1).isNotEqualTo(userHealthStatus2);
        userHealthStatus1.setId(null);
        assertThat(userHealthStatus1).isNotEqualTo(userHealthStatus2);
    }
}
