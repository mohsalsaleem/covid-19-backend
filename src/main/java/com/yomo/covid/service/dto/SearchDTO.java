package com.yomo.covid.service.dto;

import com.yomo.covid.domain.UserHealthStatus;
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

    private String locationName;

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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

}
