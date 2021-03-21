package com.haufe.beerlovers.domain.service;

import com.haufe.beerlovers.domain.model.Manufacturer;
import com.haufe.beerlovers.domain.model.ManufacturerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class ManufacturerService {

    private static final int PAGE_SIZE = 5;
    private static final String DEFAULT_SORT_FIELD = "name";
    private static final String DESC_SORT = "desc";

    private ManufacturerRepository repository;

    @Inject
    public ManufacturerService(final ManufacturerRepository repository) {
        this.repository = repository;
    }

    public Manufacturer createManufacturer(final Manufacturer manufacturer) {
        return repository.save(manufacturer);
    }

    public Optional<Manufacturer> getManufacturer(final String manufacturerId) {
        return repository.findById(manufacturerId);
    }

    public Page<Manufacturer> getAllManufacturers(final Integer page, final String sortDirection, final String... sortFields) {
        int pageNumber = 0;
        Sort.Direction direction = Sort.Direction.ASC;
        String[] fields = new String[]{DEFAULT_SORT_FIELD};

        if (sortDirection != null && sortDirection.equals(DESC_SORT)) {
            direction = Sort.Direction.DESC;
        }

        if (sortFields != null) {
            fields = sortFields;
        }

        if (page != null && page > -1) {
            pageNumber = page.intValue();
        }

        return repository.findAll(PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(direction, fields)));
    }

    public Manufacturer updateManufacturer(final Manufacturer manufacturer, final String name, final String nationality) {
        if (name != null) {
            manufacturer.setName(name);
        }

        if (nationality != null) {
            manufacturer.setNationality(nationality);
        }

        return repository.save(manufacturer);
    }

    public void removeManufacturer(final String manufacturerId) {
        repository.findById(manufacturerId).ifPresent(manufacturer -> repository.delete(manufacturer));
    }
}
