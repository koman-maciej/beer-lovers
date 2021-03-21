package com.haufe.beerlovers.controller.rest;

import com.haufe.beerlovers.config.ApiVersion;
import com.haufe.beerlovers.controller.rest.dto.CreateManufacturerDto;
import com.haufe.beerlovers.controller.rest.dto.UpdateManufacturerDto;
import com.haufe.beerlovers.domain.exception.ManufacturerNotFound;
import com.haufe.beerlovers.domain.model.Manufacturer;
import com.haufe.beerlovers.domain.service.ManufacturerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ApiVersion.V1)
@Api("manufacturers")
public class ManufacturerResource {

    private ManufacturerService manufacturerService;

    @Inject
    public ManufacturerResource(final ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping(value = "/manufacturers", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Manufacturer createManufacturer(@RequestBody final CreateManufacturerDto manufacturerDto) {
        return manufacturerService.createManufacturer(new Manufacturer(manufacturerDto.getName(),
                manufacturerDto.getNationality(), Collections.emptyList()));
    }

    @GetMapping(value = "/manufacturers/{manufacturerId}", produces = APPLICATION_JSON_VALUE)
    public Manufacturer getManufacturer(@PathVariable final String manufacturerId) {
        return manufacturerService.getManufacturer(manufacturerId).orElseThrow(ManufacturerNotFound::new);
    }

    @GetMapping(value = "/manufacturers", produces = APPLICATION_JSON_VALUE)
    public Page<Manufacturer> getAllManufacturers(@RequestParam(required = false) @ApiParam(defaultValue = "0") final Integer page,
                                                  @RequestParam(required = false) @ApiParam(defaultValue = "asc") final String sortDirection,
                                                  @RequestParam(required = false) @ApiParam(defaultValue = "name") final String... sortFields) {
        return manufacturerService.getAllManufacturers(page, sortDirection, sortFields);
    }

    @PatchMapping(value = "/manufacturers/{manufacturerId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Manufacturer updateManufacturer(@PathVariable final String manufacturerId, @RequestBody final UpdateManufacturerDto manufacturerDto) {
        final Manufacturer manufacturer = manufacturerService.getManufacturer(manufacturerId)
                .orElseThrow(ManufacturerNotFound::new);

        return manufacturerService.updateManufacturer(manufacturer,
                manufacturerDto.getName(),
                manufacturerDto.getNationality());
    }

    @DeleteMapping(value = "/manufacturers/{manufacturerId}")
    public void removeManufacturer(@PathVariable final String manufacturerId) {
        manufacturerService.removeManufacturer(manufacturerId);
    }
}