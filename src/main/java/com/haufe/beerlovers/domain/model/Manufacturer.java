package com.haufe.beerlovers.domain.model;

import org.springframework.data.annotation.Id;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Manufacturer {

    @Id
    private String id;
    private String name;
    private String nationality;
    private final List<Beer> beers;

    public Manufacturer(final String name, final String nationality, final List<Beer> beers) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.nationality = nationality;
        this.beers = beers;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public List<Beer> getBeers() {
        return beers;
    }
}
