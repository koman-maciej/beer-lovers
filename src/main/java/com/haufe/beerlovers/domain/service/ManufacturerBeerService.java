package com.haufe.beerlovers.domain.service;

import com.haufe.beerlovers.domain.exception.BeerBadRequest;
import com.haufe.beerlovers.domain.model.Beer;
import com.haufe.beerlovers.domain.model.Manufacturer;
import com.haufe.beerlovers.domain.model.ManufacturerRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Optional;

@Service
public class ManufacturerBeerService {

    private ManufacturerRepository repository;

    @Inject
    public ManufacturerBeerService(final ManufacturerRepository repository) {
        this.repository = repository;
    }

    public Beer createBeer(final Manufacturer manufacturer, final Beer beer) {
        manufacturer.getBeers().add(beer);
        repository.save(manufacturer);
        return beer;
    }

    public Optional<Beer> getBeer(final Manufacturer manufacturer, final String beerId) {
        return findBeer(manufacturer, beerId);
    }

    public Beer updateBeer(final Manufacturer manufacturer, final Beer beer, final String name,
                           final String graduation, final String type, final String description) {
        if (name != null) {
            beer.setName(name);
        }

        if (graduation != null) {
            beer.setGraduation(graduation);
        }

        if (type != null) {
            beer.setGraduation(type);
        }

        if (description != null) {
            beer.setGraduation(description);
        }

        repository.save(manufacturer);
        return beer;
    }

    public void updateBeerPicture(final Manufacturer manufacturer, final Beer beer, final MultipartFile file) {
        try {
            beer.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            repository.save(manufacturer);
        } catch (IOException e) {
            throw new BeerBadRequest();
        }
    }

    public void removeBeer(final Manufacturer manufacturer, final String beerId) {
        findBeer(manufacturer, beerId).ifPresent(beer -> {
            manufacturer.getBeers().remove(beer);
            repository.save(manufacturer);
        });
    }

    private Optional<Beer> findBeer(final Manufacturer manufacturer, final String beerId) {
        return manufacturer.getBeers().stream()
                    .filter(beer -> beerId.equals(beer.getId()))
                    .findAny();
    }
}
