package com.haufe.beerlovers.controller.rest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeResourceTest {

	private HomeResource resource = new HomeResource();

	@Test
	void createManufacturerTest() {
		String result = resource.home();

		assertEquals("Beer lovers home page", result);
	}
}
