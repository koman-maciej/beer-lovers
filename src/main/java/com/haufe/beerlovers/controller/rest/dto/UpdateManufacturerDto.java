package com.haufe.beerlovers.controller.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateManufacturerDto {

    private final String name;
    private final String nationality;

    @JsonCreator
    public UpdateManufacturerDto(@JsonProperty final String name,
                                 @JsonProperty final String nationality) {
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
