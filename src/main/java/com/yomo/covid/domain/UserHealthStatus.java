package com.yomo.covid.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.yomo.covid.domain.enumeration.UserHealthSeverity;

/**
 * A UserHealthStatus.
 */
@Document(collection = "user_health_status")
public class UserHealthStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("user_id")
    private String userId;

    @NotNull
    @Field("severity")
    private UserHealthSeverity severity;

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

    public UserHealthStatus userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserHealthSeverity getSeverity() {
        return severity;
    }

    public UserHealthStatus severity(UserHealthSeverity severity) {
        this.severity = severity;
        return this;
    }

    public void setSeverity(UserHealthSeverity severity) {
        this.severity = severity;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserHealthStatus)) {
            return false;
        }
        return id != null && id.equals(((UserHealthStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserHealthStatus{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", severity='" + getSeverity() + "'" +
            "}";
    }
}
