package com.haufe.beerlovers.controller.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBeerDto {

    private final String name;
    private final String graduation;
    private final String type;
    private final String description;

    @JsonCreator
    public CreateBeerDto(@JsonProperty(required = true) final String name,
                         @JsonProperty(required = true) final String graduation,
                         @JsonProperty(required = true) final String type,
                         @JsonProperty(required = true) final String description) {
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
