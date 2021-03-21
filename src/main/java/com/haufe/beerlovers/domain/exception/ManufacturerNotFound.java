package com.haufe.beerlovers.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Manufacturer has not been found")
public class ManufacturerNotFound extends RuntimeException {


}
