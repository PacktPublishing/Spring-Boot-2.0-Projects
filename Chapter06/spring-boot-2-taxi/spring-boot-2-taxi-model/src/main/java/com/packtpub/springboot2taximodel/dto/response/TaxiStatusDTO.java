package com.packtpub.springboot2taximodel.dto.response;

import com.packtpub.springboot2taximodel.enums.TaxiStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiStatusDTO {
    private String taxiId;

    private TaxiStatus status;
}
