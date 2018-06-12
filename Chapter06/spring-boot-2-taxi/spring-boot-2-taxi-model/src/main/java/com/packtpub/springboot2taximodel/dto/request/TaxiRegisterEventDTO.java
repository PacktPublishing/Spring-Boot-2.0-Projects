package com.packtpub.springboot2taximodel.dto.request;

import com.packtpub.springboot2taximodel.enums.TaxiType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiRegisterEventDTO {
    private String taxiId = UUID.randomUUID().toString();

    private TaxiType taxiType;
}
