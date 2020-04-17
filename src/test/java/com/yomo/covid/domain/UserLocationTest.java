package com.yomo.covid.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yomo.covid.web.rest.TestUtil;

public class UserLocationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserLocation.class);
        UserLocation userLocation1 = new UserLocation();
        userLocation1.setId("id1");
        UserLocation userLocation2 = new UserLocation();
        userLocation2.setId(userLocation1.getId());
        assertThat(userLocation1).isEqualTo(userLocation2);
        userLocation2.setId("id2");
        assertThat(userLocation1).isNotEqualTo(userLocation2);
        userLocation1.setId(null);
        assertThat(userLocation1).isNotEqualTo(userLocation2);
    }
}
