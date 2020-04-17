package com.yomo.covid.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.yomo.covid.domain.UserCurrentLocation} entity.
 */
public class UserCurrentLocationDTO implements Serializable {
    
    private String id;

    private String userId;

    private String locationId;

    
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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserCurrentLocationDTO userCurrentLocationDTO = (UserCurrentLocationDTO) o;
        if (userCurrentLocationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userCurrentLocationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserCurrentLocationDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", locationId='" + getLocationId() + "'" +
            "}";
    }
}
