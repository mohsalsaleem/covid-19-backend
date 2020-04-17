package com.yomo.covid.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.yomo.covid.domain.enumeration.UserHealthSeverity;

/**
 * A DTO for the {@link com.yomo.covid.domain.UserHealthStatus} entity.
 */
public class UserHealthStatusDTO implements Serializable {
    
    private String id;

    private String userId;

    private UserHealthSeverity severity;

    
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

    public UserHealthSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(UserHealthSeverity severity) {
        this.severity = severity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserHealthStatusDTO userHealthStatusDTO = (UserHealthStatusDTO) o;
        if (userHealthStatusDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userHealthStatusDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserHealthStatusDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", severity='" + getSeverity() + "'" +
            "}";
    }
}
