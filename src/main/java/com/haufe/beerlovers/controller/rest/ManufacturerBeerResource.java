package com.haufe.beerlovers.controller.rest;

import com.haufe.beerlovers.config.ApiVersion;
import com.haufe.beerlovers.controller.rest.dto.CreateBeerDto;
import com.haufe.beerlovers.controller.rest.dto.UpdateBeerDto;
import com.haufe.beerlovers.domain.exception.BeerNotFound;
import com.haufe.beerlovers.domain.exception.ManufacturerNotFound;
import com.haufe.beerlovers.domain.model.Beer;
import com.haufe.beerlovers.domain.model.Manufacturer;
import com.haufe.beerlovers.domain.service.ManufacturerBeerService;
import com.haufe.beerlovers.domain.service.ManufacturerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(ApiVersion.V1 + "/manufacturers/{manufacturerId}")
@Api("manufacturers-beers")
public class ManufacturerBeerResource {

    private ManufacturerBeerService manufacturerBeerService;
    private ManufacturerService manufacturerService;

    @Inject
    public ManufacturerBeerResource(final ManufacturerBeerService manufacturerBeerService, final ManufacturerService manufacturerService) {
        this.manufacturerBeerService = manufacturerBeerService;
        this.manufacturerService = manufacturerService;
    }

    @PostMapping(value = "/beers", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Beer createBeer(@PathVariable final String manufacturerId, @RequestBody final CreateBeerDto beerDto) {
        final Manufacturer manufacturer = manufacturerService.getManufacturer(manufacturerId).orElseThrow(ManufacturerNotFound::new);
        return manufacturerBeerService.createBeer(manufacturer,
                new Beer(beerDto.getName(), beerDto.getGraduation(), beerDto.getType(), beerDto.getDescription()));
    }

    @GetMapping(value = "/beers/{beerId}", produces = APPLICATION_JSON_VALUE)
    public Beer getBeer(@PathVariable final String manufacturerId, @PathVariable final String beerId) {
        final Manufacturer manufacturer = manufacturerService.getManufacturer(manufacturerId).orElseThrow(ManufacturerNotFound::new);
        return manufacturerBeerService.getBeer(manufacturer, beerId).orElseThrow(BeerNotFound::new);
    }

    @PatchMapping(value = "/beers/{beerId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Beer updateBeer(@PathVariable final String manufacturerId, @PathVariable final String beerId, @RequestBody final UpdateBeerDto beerDto) {
        final Manufacturer manufacturer = manufacturerService.getManufacturer(manufacturerId).orElseThrow(ManufacturerNotFound::new);
        final Beer beer = manufacturerBeerService.getBeer(manufacturer, beerId).orElseThrow(BeerNotFound::new);
        return manufacturerBeerService.updateBeer(manufacturer, beer, beerDto.getName(), beerDto.getGraduation(),
                beerDto.getType(), beerDto.getDescription());
    }

    @DeleteMapping(value = "/beers/{beerId}")
    public void removeBeer(@PathVariable final String manufacturerId, @PathVariable final String beerId) {
        manufacturerService.getManufacturer(manufacturerId).ifPresent(manufacturer -> {
            manufacturerBeerService.removeBeer(manufacturer, beerId);
        });
    }

    @PostMapping(value = "/beers/{beerId}/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addBeerPicture(@PathVariable final String manufacturerId, @PathVariable final String beerId, @RequestParam final MultipartFile image) {
        final Manufacturer manufacturer = manufacturerService.getManufacturer(manufacturerId).orElseThrow(ManufacturerNotFound::new);
        final Beer beer = manufacturerBeerService.getBeer(manufacturer, beerId).orElseThrow(BeerNotFound::new);
        manufacturerBeerService.updateBeerPicture(manufacturer, beer, image);
    }
}