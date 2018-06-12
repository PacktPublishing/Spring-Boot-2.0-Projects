package com.packtpub.springboot2taxiservice.repo;

import com.packtpub.springboot2taxiservice.model.TaxiBooking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiBookingRepository extends CrudRepository<TaxiBooking, String> {

}
