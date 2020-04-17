package com.yomo.covid.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.yomo.covid.domain.UserSymptom} entity.
 */
public class UserSymptomDTO implements Serializable {
    
    private String id;

    private String userId;

    private String symptom;

    
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

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserSymptomDTO userSymptomDTO = (UserSymptomDTO) o;
        if (userSymptomDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userSymptomDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserSymptomDTO{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", symptom='" + getSymptom() + "'" +
            "}";
    }
}
