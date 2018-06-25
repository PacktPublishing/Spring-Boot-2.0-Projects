package com.packtpub.springboot2twitterclone.controller;

import com.packtpub.springboot2twitterclone.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(e.getMessage());
        errorDTO.setStatusCode(500);
        return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
