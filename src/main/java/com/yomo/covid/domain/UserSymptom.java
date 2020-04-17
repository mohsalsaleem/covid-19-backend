package com.yomo.covid.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UserSymptom.
 */
@Document(collection = "user_symptom")
public class UserSymptom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("user_id")
    private String userId;

    @NotNull
    @Field("symptom")
    private String symptom;

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

    public UserSymptom userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSymptom() {
        return symptom;
    }

    public UserSymptom symptom(String symptom) {
        this.symptom = symptom;
        return this;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserSymptom)) {
            return false;
        }
        return id != null && id.equals(((UserSymptom) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserSymptom{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", symptom='" + getSymptom() + "'" +
            "}";
    }
}
