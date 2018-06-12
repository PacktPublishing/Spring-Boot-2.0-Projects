package com.packtpub.springboot2taxiservice.repo;

import com.packtpub.springboot2taxiservice.model.Taxi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends CrudRepository<Taxi, String> {

}
