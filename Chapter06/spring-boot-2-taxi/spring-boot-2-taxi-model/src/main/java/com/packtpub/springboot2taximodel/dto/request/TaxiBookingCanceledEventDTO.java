package com.packtpub.springboot2taximodel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiBookingCanceledEventDTO {

    private String taxiBookingId;

    private String reason;

    private Date cancelTime = new Date();

}
