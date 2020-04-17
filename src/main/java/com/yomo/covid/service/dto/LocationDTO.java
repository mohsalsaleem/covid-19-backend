package com.yomo.covid.service.dto;

import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class LocationDTO implements Serializable {

    @NotNull
    private String type;

    @NotNull
    private List<Long> coordinates;

    public String getType() {
        return "Point";
    }

    public void setType(String type) {
        this.type = "Point";
    }

    public List<Long> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Long> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
            "type='" + type + '\'' +
            ", coordinates=" + coordinates +
            '}';
    }
}
