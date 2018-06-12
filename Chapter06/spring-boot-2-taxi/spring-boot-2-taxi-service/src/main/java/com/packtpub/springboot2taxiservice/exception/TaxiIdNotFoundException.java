package com.packtpub.springboot2taxiservice.exception;

public class TaxiIdNotFoundException extends RuntimeException {
    public TaxiIdNotFoundException(String message) {
        super(message);
    }

    public TaxiIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
