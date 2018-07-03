package com.packtpub.spring.boot.async.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResizeRequest {

    private Integer width;

    private Integer height;

    private String inputFile;

}
