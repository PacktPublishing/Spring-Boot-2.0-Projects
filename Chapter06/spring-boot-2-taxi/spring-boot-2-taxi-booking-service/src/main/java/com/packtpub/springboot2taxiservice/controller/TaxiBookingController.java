package com.packtpub.springboot2taxiservice.controller;

import com.packtpub.springboot2taximodel.dto.request.TaxiBookedEventDTO;
import com.packtpub.springboot2taximodel.dto.request.TaxiBookingAcceptedEventDTO;
import com.packtpub.springboot2taximodel.dto.request.TaxiBookingCanceledEventDTO;
import com.packtpub.springboot2taximodel.dto.response.TaxiBookedEventResponseDTO;
import com.packtpub.springboot2taximodel.dto.response.TaxiBookingAcceptedEventResponseDTO;
import com.packtpub.springboot2taximodel.dto.response.TaxiBookingCanceledEventResponseDTO;
import com.packtpub.springboot2taximodel.dto.response.TaxiBookingResponseDTO;
import com.packtpub.springboot2taximodel.enums.TaxiType;
import com.packtpub.springboot2taxiservice.service.TaxiBookingService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/taxibookings")
@RestController
public class TaxiBookingController {

    private final TaxiBookingService taxiBookingService;

    public TaxiBookingController(TaxiBookingService taxiBookingService) {
        this.taxiBookingService = taxiBookingService;
    }

    @PostMapping
    public Mono<TaxiBookedEventResponseDTO> book(@RequestBody TaxiBookedEventDTO taxiBookedEventDTO) {
        return taxiBookingService.book(taxiBookedEventDTO).map(t -> new TaxiBookedEventResponseDTO(t.getTaxiBookingId()));
    }

    @PutMapping("/{taxiBookingId}/cancel")
    public Mono<TaxiBookingCanceledEventResponseDTO> cancel(@PathVariable("taxiBookingId") String taxiBookingId, @RequestBody TaxiBookingCanceledEventDTO taxiBookingCanceledEventDTO) {
        return taxiBookingService.cancel(taxiBookingId, taxiBookingCanceledEventDTO).map(t -> new TaxiBookingCanceledEventResponseDTO(t.getTaxiBookingId()));
    }

    @PutMapping("/{taxiBookingId}/accept")
    public Mono<TaxiBookingAcceptedEventResponseDTO> accept(@PathVariable("taxiBookingId") String taxiBookingId, @RequestBody TaxiBookingAcceptedEventDTO taxiBookingAcceptedEventDTO) {
        return taxiBookingService.accept(taxiBookingId, taxiBookingAcceptedEventDTO).map(t -> new TaxiBookingAcceptedEventResponseDTO(t.getTaxiBookingId(), t.getTaxiId(), t.getAcceptedTime()));
    }

    @GetMapping
    public Flux<TaxiBookingResponseDTO> getBookings(@RequestParam("type") TaxiType taxiType, @RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude, @RequestParam(value = "radius", defaultValue = "1") Double radius) {
        return taxiBookingService.getBookings(taxiType, latitude, longitude, radius).map(r -> new TaxiBookingResponseDTO(r.getContent().getName()));
    }


}
