package com.packtpub.springboot2taxiservice.controller;

import com.packtpub.springboot2taximodel.dto.response.ErrorDTO;
import com.packtpub.springboot2taxiservice.exception.TaxiBookingIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {

    @ExceptionHandler(TaxiBookingIdNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleTaxiBookingIdNotFoundException(TaxiBookingIdNotFoundException e) {
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
