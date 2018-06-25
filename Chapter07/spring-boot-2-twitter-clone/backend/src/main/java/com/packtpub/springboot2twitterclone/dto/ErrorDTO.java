package com.packtpub.springboot2twitterclone.dto;

import lombok.Data;

@Data
public class ErrorDTO {

    private Integer statusCode;
    private String message;

}
