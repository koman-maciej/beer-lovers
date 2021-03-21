package com.haufe.beerlovers.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.Binary;

import java.util.UUID;

public class Beer {

    private String id;
    private String name;
    private String graduation;
    private String type;
    private String description;
    @JsonProperty()
    private Binary image;

    public Beer(final String name, final String graduation, final String type, final String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.graduation = graduation;
        this.type = type;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(final String graduation) {
        this.graduation = graduation;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Binary getImage() {
        return image;
    }

    public void setImage(final Binary image) {
        this.image = image;
    }
}
