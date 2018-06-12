package com.packtpub.springboot2taximodel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiBookingAcceptedEventDTO {

    private String taxiBookingId;

    private String taxiId;

    private Date acceptedTime = new Date();

}
