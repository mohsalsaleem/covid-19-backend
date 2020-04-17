package com.yomo.covid.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.yomo.covid.web.rest.TestUtil;

public class UserTravelHistoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserTravelHistory.class);
        UserTravelHistory userTravelHistory1 = new UserTravelHistory();
        userTravelHistory1.setId("id1");
        UserTravelHistory userTravelHistory2 = new UserTravelHistory();
        userTravelHistory2.setId(userTravelHistory1.getId());
        assertThat(userTravelHistory1).isEqualTo(userTravelHistory2);
        userTravelHistory2.setId("id2");
        assertThat(userTravelHistory1).isNotEqualTo(userTravelHistory2);
        userTravelHistory1.setId(null);
        assertThat(userTravelHistory1).isNotEqualTo(userTravelHistory2);
    }
}
