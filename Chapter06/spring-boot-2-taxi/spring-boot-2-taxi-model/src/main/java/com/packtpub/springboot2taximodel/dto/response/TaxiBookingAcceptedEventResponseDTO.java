package com.packtpub.springboot2taximodel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiBookingAcceptedEventResponseDTO {

    private String taxiBookingId;

    private String taxiId;

    private Date acceptedTime;
}
