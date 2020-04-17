package com.yomo.covid.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UserCurrentLocation.
 */
@Document(collection = "user_current_location")
public class UserCurrentLocation extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("user_id")
    private String userId;

    @Field("location_id")
    private String locationId;

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

    public UserCurrentLocation userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocationId() {
        return locationId;
    }

    public UserCurrentLocation locationId(String locationId) {
        this.locationId = locationId;
        return this;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCurrentLocation)) {
            return false;
        }
        return id != null && id.equals(((UserCurrentLocation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserCurrentLocation{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", locationId='" + getLocationId() + "'" +
            "}";
    }
}
