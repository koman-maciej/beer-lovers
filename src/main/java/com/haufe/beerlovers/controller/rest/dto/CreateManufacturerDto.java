package com.haufe.beerlovers.controller.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateManufacturerDto {

    private final String name;
    private final String nationality;

    @JsonCreator
    public CreateManufacturerDto(@JsonProperty(required = true) final String name,
                                 @JsonProperty(required = true) final String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }
}
