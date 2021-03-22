package com.haufe.beerlovers.controller.rest;

import com.haufe.beerlovers.controller.rest.dto.CreateBeerDto;
import com.haufe.beerlovers.controller.rest.dto.UpdateBeerDto;
import com.haufe.beerlovers.domain.exception.BeerImageBadRequest;
import com.haufe.beerlovers.domain.exception.BeerNotFound;
import com.haufe.beerlovers.domain.exception.ManufacturerNotFound;
import com.haufe.beerlovers.domain.model.Beer;
import com.haufe.beerlovers.domain.model.Manufacturer;
import com.haufe.beerlovers.domain.service.ManufacturerBeerService;
import com.haufe.beerlovers.domain.service.ManufacturerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@ExtendWith(MockitoExtension.class)
class ManufacturerBeerResourceTest {
    @Mock
    private ManufacturerService manufacturerService;
    @Mock
    private ManufacturerBeerService manufacturerBeerService;

    @InjectMocks
    private ManufacturerBeerResource resource;

    private final CreateBeerDto createBeerDto = new CreateBeerDto("name", "graduation", "type", "desc");
    private final UpdateBeerDto updateBeerDto = new UpdateBeerDto("updatedName", "updatedGraduation", "updatedType", "updatedDesc");
    private final Manufacturer manufacturer1 = new Manufacturer("name", "nationality", Collections.emptyList());
    private final Beer beer1 = new Beer("name", "graduation", "type", "desc");
    private final Beer beer2 = new Beer("updatedName", "updatedGraduation", "updatedType", "updatedDesc");

    @Test
    void createBeerTest() {
        String id = "id";
        when(manufacturerService.getManufacturer(id)).thenReturn(Optional.of(manufacturer1));
        when(manufacturerBeerService.createBeer(eq(manufacturer1), any(Beer.class))).thenReturn(beer1);

        Beer result = resource.createBeer(id, createBeerDto);

        assertNotNull(result);
        assertEquals(beer1.getId(), result.getId());
        assertEquals(beer1.getName(), result.getName());
        assertEquals(beer1.getGraduation(), result.getGraduation());
        assertEquals(beer1.getType(), result.getType());
        assertEquals(beer1.getDescription(), result.getDescription());
        assertEquals(beer1.getImage(), result.getImage());
    }

    @Test
    void createBeerWhenManufacturerNotFoundTest() {
        String id = "id";
        when(manufacturerService.getManufacturer(id)).thenReturn(Optional.empty());

        assertThrows(ManufacturerNotFound.class, () -> resource.createBeer(id, createBeerDto));
    }

    @Test
    void getBeerTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        when(manufacturerService.getManufacturer(manufacturerId)).thenReturn(Optional.of(manufacturer1));
        when(manufacturerBeerService.getBeer(manufacturer1, beerId)).thenReturn(Optional.of(beer1));

        Beer result = resource.getBeer(manufacturerId, beerId);

        assertNotNull(result);
        assertEquals(beer1.getId(), result.getId());
        assertEquals(beer1.getName(), result.getName());
        assertEquals(beer1.getGraduation(), result.getGraduation());
        assertEquals(beer1.getType(), result.getType());
        assertEquals(beer1.getDescription(), result.getDescription());
        assertEquals(beer1.getImage(), result.getImage());
    }

    @Test
    void getBeerWhenManufacturerNotFoundTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        when(manufacturerService.getManufacturer(manufacturerId)).thenReturn(Optional.empty());

        assertThrows(ManufacturerNotFound.class, () -> resource.getBeer(manufacturerId, beerId));
    }

    @Test
    void getBeerWhenBeerNotFoundTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        when(manufacturerService.getManufacturer(manufacturerId)).thenReturn(Optional.of(manufacturer1));
        when(manufacturerBeerService.getBeer(manufacturer1, beerId)).thenReturn(Optional.empty());

        assertThrows(BeerNotFound.class, () -> resource.getBeer(manufacturerId, beerId));
    }

    @Test
    void updateBeerTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        when(manufacturerService.getManufacturer(manufacturerId)).thenReturn(Optional.of(manufacturer1));
        when(manufacturerBeerService.getBeer(manufacturer1, beerId)).thenReturn(Optional.of(beer1));
        when(manufacturerBeerService.updateBeer(manufacturer1, beer1, updateBeerDto.getName(), updateBeerDto.getGraduation(), updateBeerDto.getType(), updateBeerDto.getDescription())).thenReturn(beer2);

        Beer result = resource.updateBeer(manufacturerId, beerId, updateBeerDto);

        assertNotNull(result);
        assertEquals(beer2.getId(), result.getId());
        assertEquals(beer2.getName(), result.getName());
        assertEquals(beer2.getGraduation(), result.getGraduation());
        assertEquals(beer2.getType(), result.getType());
        assertEquals(beer2.getDescription(), result.getDescription());
        assertEquals(beer2.getImage(), result.getImage());
    }

    @Test
    void updateBeerWhenManufacturerNotFoundTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        when(manufacturerService.getManufacturer(manufacturerId)).thenReturn(Optional.empty());

        assertThrows(ManufacturerNotFound.class, () -> resource.updateBeer(manufacturerId, beerId, updateBeerDto));
    }

    @Test
    void updateBeerWhenBeerNotFoundTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        when(manufacturerService.getManufacturer(manufacturerId)).thenReturn(Optional.of(manufacturer1));
        when(manufacturerBeerService.getBeer(manufacturer1, beerId)).thenReturn(Optional.empty());

        assertThrows(BeerNotFound.class, () -> resource.updateBeer(manufacturerId, beerId, updateBeerDto));
    }

    @Test
    void removeBeerTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        when(manufacturerService.getManufacturer(manufacturerId)).thenReturn(Optional.of(manufacturer1));

        assertDoesNotThrow(() -> resource.removeBeer(manufacturerId, beerId));
    }

    @Test
    void removeBeerWhenManufacturerNotFoundTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        when(manufacturerService.getManufacturer(manufacturerId)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> resource.removeBeer(manufacturerId, beerId));
    }

    @Test
    void addBeerPictureTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        MockMultipartFile file = new MockMultipartFile("name", "", IMAGE_PNG_VALUE, new byte[1]);
        when(manufacturerService.getManufacturer(manufacturerId)).thenReturn(Optional.of(manufacturer1));
        when(manufacturerBeerService.getBeer(manufacturer1, beerId)).thenReturn(Optional.of(beer1));

        assertDoesNotThrow(() -> resource.addBeerPicture(manufacturerId, beerId, file));
    }

    @Test
    void addBeerPictureWhenFileIsWrongTypeTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        MultipartFile file = new MockMultipartFile("name", new byte[1]);

        assertThrows(BeerImageBadRequest.class, () -> resource.addBeerPicture(manufacturerId, beerId, file));
    }

    @Test
    void addBeerPictureWhenManufacturerNotFoundTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        MockMultipartFile file = new MockMultipartFile("name", "", IMAGE_PNG_VALUE, new byte[1]);
        when(manufacturerService.getManufacturer(manufacturerId)).thenReturn(Optional.empty());

        assertThrows(ManufacturerNotFound.class, () -> resource.addBeerPicture(manufacturerId, beerId, file));
    }

    @Test
    void addBeerPictureWhenBeerNotFoundTest() {
        String manufacturerId = "manufacturerId";
        String beerId = "beerId";
        MockMultipartFile file = new MockMultipartFile("name", "", IMAGE_PNG_VALUE, new byte[1]);
        when(manufacturerService.getManufacturer(manufacturerId)).thenReturn(Optional.of(manufacturer1));
        when(manufacturerBeerService.getBeer(manufacturer1, beerId)).thenReturn(Optional.empty());

        assertThrows(BeerNotFound.class, () -> resource.addBeerPicture(manufacturerId, beerId, file));
    }
}
