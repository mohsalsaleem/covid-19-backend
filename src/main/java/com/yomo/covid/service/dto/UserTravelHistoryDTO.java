package com.yomo.covid.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.yomo.covid.domain.UserTravelHistory} entity.
 */
public class UserTravelHistoryDTO implements Serializable {
    
    private String id;

    private String userId;

    private String locationName;

    private Long latitude;

    private Long longitude;

    private Instant dateAndTimeOfTravel;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Instant getDateAndTimeOfTravel() {
        return dateAndTimeOfTravel;
    }

    public void setDateAndTimeOfTravel(Instant dateAndTimeOfTravel) {
        this.dateAndTimeOfTravel = dateAndTimeOfTravel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserTravelHistoryDTO userTravelHistoryDTO = (UserTravelHistoryDTO) o;
        if (userTravelHistoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userTravelHistoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserTravelHistoryDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", locationName='" + getLocationName() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", dateAndTimeOfTravel='" + getDateAndTimeOfTravel() + "'" +
            "}";
    }
}
