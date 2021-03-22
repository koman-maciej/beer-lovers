package com.haufe.beerlovers.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The image type has not been recognized")
public class BeerImageBadRequest extends RuntimeException {
}
