package com.yomo.covid.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yomo.covid.web.rest.TestUtil;

public class UserCurrentLocationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCurrentLocation.class);
        UserCurrentLocation userCurrentLocation1 = new UserCurrentLocation();
        userCurrentLocation1.setId("id1");
        UserCurrentLocation userCurrentLocation2 = new UserCurrentLocation();
        userCurrentLocation2.setId(userCurrentLocation1.getId());
        assertThat(userCurrentLocation1).isEqualTo(userCurrentLocation2);
        userCurrentLocation2.setId("id2");
        assertThat(userCurrentLocation1).isNotEqualTo(userCurrentLocation2);
        userCurrentLocation1.setId(null);
        assertThat(userCurrentLocation1).isNotEqualTo(userCurrentLocation2);
    }
}
