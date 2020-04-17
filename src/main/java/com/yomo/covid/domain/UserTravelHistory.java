package com.yomo.covid.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A UserTravelHistory.
 */
@Document(collection = "user_travel_history")
public class UserTravelHistory extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("user_id")
    private String userId;

    @Field("location_name")
    private String locationName;

    @Field("latitude")
    private Long latitude;

    @Field("longitude")
    private Long longitude;

    @Field("date_and_time_of_travel")
    private Instant dateAndTimeOfTravel;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public UserTravelHistory userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocationName() {
        return locationName;
    }

    public UserTravelHistory locationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Long getLatitude() {
        return latitude;
    }

    public UserTravelHistory latitude(Long latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public UserTravelHistory longitude(Long longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Instant getDateAndTimeOfTravel() {
        return dateAndTimeOfTravel;
    }

    public UserTravelHistory dateAndTimeOfTravel(Instant dateAndTimeOfTravel) {
        this.dateAndTimeOfTravel = dateAndTimeOfTravel;
        return this;
    }

    public void setDateAndTimeOfTravel(Instant dateAndTimeOfTravel) {
        this.dateAndTimeOfTravel = dateAndTimeOfTravel;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserTravelHistory)) {
            return false;
        }
        return id != null && id.equals(((UserTravelHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserTravelHistory{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", locationName='" + getLocationName() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", dateAndTimeOfTravel='" + getDateAndTimeOfTravel() + "'" +
            "}";
    }
}
