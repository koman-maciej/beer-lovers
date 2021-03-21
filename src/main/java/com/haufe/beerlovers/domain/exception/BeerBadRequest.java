package com.haufe.beerlovers.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad request for beer parameter")
public class BeerBadRequest extends RuntimeException {
}
