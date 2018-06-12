package com.packtpub.springboot2taxiservice;

import com.packtpub.springboot2taxiconfig.config.RedisConfig;
import com.packtpub.springboot2taximodel.enums.TaxiStatus;
import com.packtpub.springboot2taximodel.enums.TaxiType;
import com.packtpub.springboot2taximodel.util.LocationGenerator;
import com.packtpub.springboot2taxiservice.listener.TaxiBookingAcceptedEventMessageListener;
import com.packtpub.springboot2taxiservice.model.Taxi;
import com.packtpub.springboot2taxiservice.repo.TaxiRepository;
import com.packtpub.springboot2taxiservice.service.TaxiService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.UUID;

@SpringBootApplication
@Import({RedisConfig.class})
public class SpringBoot2TaxiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2TaxiServiceApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(TaxiRepository taxiRepository, TaxiService taxiService) {
		return args -> {
			taxiRepository.deleteAll();

			taxiRepository.save(new Taxi(UUID.randomUUID().toString(), TaxiType.MINI, TaxiStatus.AVAILABLE));
			taxiRepository.save(new Taxi(UUID.randomUUID().toString(), TaxiType.NANO, TaxiStatus.AVAILABLE));
			taxiRepository.save(new Taxi(UUID.randomUUID().toString(), TaxiType.VAN, TaxiStatus.AVAILABLE));

			Iterable<Taxi> taxis = taxiRepository.findAll();

			taxis.forEach(t -> {
				taxiService.updateLocation(t.getTaxiId(), LocationGenerator.getLocation(79.865072, 6.927610, 3000)).subscribe();
			});
		};
	}

	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, TaxiBookingAcceptedEventMessageListener taxiBookingAcceptedEventMessageListener) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(taxiBookingAcceptedEventMessageListener, new PatternTopic(RedisConfig.ACCEPTED_EVENT_CHANNEL));
		return container;
	}

}
