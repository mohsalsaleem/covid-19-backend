package com.yomo.covid.service.dto;

import com.yomo.covid.domain.UserTravelHistory;
import com.yomo.covid.domain.enumeration.UserHealthSeverity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Search DTO to find user by
 * severity,
 * age group,
 * contact with someone,
 * travel history to affected areas
 */
public class SearchDTO implements Serializable {
    private Set<UserHealthSeverity> userHealthSeveritySet;

    private AgeGroup ageGroup;

    private List<UserDTO> contactWithUsers;

    private List<UserTravelHistoryDTO> userTravelHistoryDTOS;

    public Set<UserHealthSeverity> getUserHealthSeveritySet() {
        return userHealthSeveritySet;
    }

    public void setUserHealthSeveritySet(Set<UserHealthSeverity> userHealthSeveritySet) {
        this.userHealthSeveritySet = userHealthSeveritySet;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public List<UserDTO> getContactWithUsers() {
        return contactWithUsers;
    }

    public void setContactWithUsers(List<UserDTO> contactWithUsers) {
        this.contactWithUsers = contactWithUsers;
    }

    public List<UserTravelHistoryDTO> getUserTravelHistoryDTOS() {
        return userTravelHistoryDTOS;
    }

    public void setUserTravelHistoryDTOS(List<UserTravelHistoryDTO> userTravelHistoryDTOS) {
        this.userTravelHistoryDTOS = userTravelHistoryDTOS;
    }

    public class AgeGroup {
        public Integer fromAge;
        public Integer toAge;
    }

}
