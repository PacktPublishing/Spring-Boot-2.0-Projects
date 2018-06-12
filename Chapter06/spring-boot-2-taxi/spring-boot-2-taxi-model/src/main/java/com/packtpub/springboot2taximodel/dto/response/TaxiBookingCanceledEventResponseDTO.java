package com.packtpub.springboot2taximodel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiBookingCanceledEventResponseDTO {

    private String taxiBookingId;

}
