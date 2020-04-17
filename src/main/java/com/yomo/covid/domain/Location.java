package com.yomo.covid.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Location {

    @NotNull
    @Field("type")
    private String type;

    @NotNull
    @Field("coordinates")
    private List<Long> coordinates;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return  "Point";
    }

    public List<Long> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Long> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Location{" +
            "type='" + type + '\'' +
            ", coordinates=" + coordinates +
            '}';
    }
}
