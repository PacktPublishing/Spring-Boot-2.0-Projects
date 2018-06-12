package com.packtpub.springboot2taxiservice;

import com.packtpub.springboot2taxiconfig.config.RedisConfig;
import com.packtpub.springboot2taximodel.dto.request.TaxiBookedEventDTO;
import com.packtpub.springboot2taximodel.enums.TaxiType;
import com.packtpub.springboot2taximodel.util.LocationGenerator;
import com.packtpub.springboot2taxiservice.service.TaxiBookingService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@Import(RedisConfig.class)
public class SpringBoot2TaxiBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2TaxiBookingServiceApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(TaxiBookingService taxiBookingService) {
		return args -> {
			for (int i = 0;i<3;i++) {
				taxiBookingService.book(new TaxiBookedEventDTO(UUID.randomUUID().toString(), LocationGenerator.getLocation(79.865072, 6.927610, 3000), LocationGenerator.getLocation(79.865072, 6.927610, 3000), new Date(), 1l, TaxiType.MINI)).subscribe();
			}
			for (int i = 0;i<3;i++) {
				taxiBookingService.book(new TaxiBookedEventDTO(UUID.randomUUID().toString(), LocationGenerator.getLocation(79.865072, 6.927610, 3000), LocationGenerator.getLocation(79.865072, 6.927610, 3000), new Date(), 1l, TaxiType.NANO)).subscribe();
			}
			for (int i = 0;i<3;i++) {
				taxiBookingService.book(new TaxiBookedEventDTO(UUID.randomUUID().toString(), LocationGenerator.getLocation(79.865072, 6.927610, 3000), LocationGenerator.getLocation(79.865072, 6.927610, 3000), new Date(), 1l, TaxiType.VAN)).subscribe();
			}
		};
	}

}
