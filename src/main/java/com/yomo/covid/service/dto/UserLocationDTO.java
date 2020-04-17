package com.yomo.covid.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.yomo.covid.domain.UserLocation} entity.
 */
public class UserLocationDTO implements Serializable {

    private String id;

    @NotNull
    private String userId;

    private Long latitude;

    private Long longitude;

    @NotNull
    private String locationName;


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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserLocationDTO userLocationDTO = (UserLocationDTO) o;
        if (userLocationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userLocationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserLocationDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", locationName='" + getLocationName() + "'" +
            "}";
    }
}
