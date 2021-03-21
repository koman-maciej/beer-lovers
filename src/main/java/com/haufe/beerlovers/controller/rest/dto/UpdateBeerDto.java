package com.haufe.beerlovers.controller.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateBeerDto {

    private final String name;
    private final String graduation;
    private final String type;
    private final String description;

    @JsonCreator
    public UpdateBeerDto(@JsonProperty final String name,
                         @JsonProperty final String graduation,
                         @JsonProperty final String type,
                         @JsonProperty final String description) {
        this.name = name;
        this.graduation = graduation;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getGraduation() {
        return graduation;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
