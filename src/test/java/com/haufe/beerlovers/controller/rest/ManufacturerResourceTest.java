package com.haufe.beerlovers.controller.rest;

import com.haufe.beerlovers.controller.rest.dto.CreateManufacturerDto;
import com.haufe.beerlovers.controller.rest.dto.UpdateManufacturerDto;
import com.haufe.beerlovers.domain.exception.ManufacturerNotFound;
import com.haufe.beerlovers.domain.model.Manufacturer;
import com.haufe.beerlovers.domain.service.ManufacturerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManufacturerResourceTest {

	@Mock
	private ManufacturerService manufacturerService;

	@InjectMocks
	private ManufacturerResource resource;

    private final CreateManufacturerDto createManufacturerDto = new CreateManufacturerDto("name", "nationality");
    private final UpdateManufacturerDto updateManufacturerDto = new UpdateManufacturerDto("updatedName", "updatedNationality");
	private final Manufacturer manufacturer1 = new Manufacturer("name", "nationality", Collections.emptyList());
	private final Manufacturer manufacturer2 = new Manufacturer("updatedName", "updatedName", Collections.emptyList());

	@Test
	void createManufacturerTest() {
		when(manufacturerService.createManufacturer(any(Manufacturer.class))).thenReturn(manufacturer1);

		Manufacturer result = resource.createManufacturer(createManufacturerDto);

		assertNotNull(result);
		assertEquals(manufacturer1.getId(), result.getId());
		assertEquals(manufacturer1.getName(), result.getName());
		assertEquals(manufacturer1.getNationality(), result.getNationality());
		assertEquals(manufacturer1.getBeers(), result.getBeers());
	}

	@Test
	void getManufacturerTest() {
		String id = "id";
		when(manufacturerService.getManufacturer(id)).thenReturn(Optional.of(manufacturer1));

		Manufacturer result = resource.getManufacturer(id);

		assertNotNull(result);
		assertEquals(manufacturer1.getId(), result.getId());
		assertEquals(manufacturer1.getName(), result.getName());
		assertEquals(manufacturer1.getNationality(), result.getNationality());
		assertEquals(manufacturer1.getBeers(), result.getBeers());
	}

	@Test
	void getAllManufacturersTest() {
		when(manufacturerService.getAllManufacturers(0, null, null)).thenReturn(Page.empty());

		Page<Manufacturer> result = resource.getAllManufacturers(0, null, null);

		assertNotNull(result);
		assertEquals(Page.empty().getSize(), result.getSize());
	}

	@Test
	void updateManufacturerTest() {
		String id = "id";
		when(manufacturerService.getManufacturer(id)).thenReturn(Optional.of(manufacturer1));
		when(manufacturerService.updateManufacturer(manufacturer1, updateManufacturerDto.getName(), updateManufacturerDto.getNationality())).thenReturn(manufacturer2);

		Manufacturer result = resource.updateManufacturer(id, updateManufacturerDto);

		assertNotNull(result);
		assertEquals(manufacturer2.getId(), result.getId());
		assertEquals(manufacturer2.getName(), result.getName());
		assertEquals(manufacturer2.getNationality(), result.getNationality());
		assertEquals(manufacturer2.getBeers(), result.getBeers());
	}

	@Test
	void updateManufacturerWhenManufacturerNotFoundTest() {
		String id = "id";
		when(manufacturerService.getManufacturer(id)).thenReturn(Optional.empty());

		assertThrows(ManufacturerNotFound.class, () -> resource.updateManufacturer(id, updateManufacturerDto));
	}

	@Test
	void removeManufacturerTest() {
		String id = "id";

		assertDoesNotThrow(() -> resource.removeManufacturer(id));
	}
}
