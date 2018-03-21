/*
    Copyright (C) 2018  Shazin Sadakath

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.packtpub.springboot2intro;

import com.packtpub.springboot2intro.endpoints.health.InternetHealthIndicator;
import com.packtpub.springboot2intro.properties.Address;
import com.packtpub.springboot2intro.properties.DemoApplicationProperties;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;

import java.util.List;

/**
 * @author Shazin Sadakath
 */
@SpringBootApplication
@EnableConfigurationProperties(DemoApplicationProperties.class)
public class SpringBoot2IntroApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringBoot2IntroApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.initializers((GenericApplicationContext genericApplicationContext) -> {
										genericApplicationContext.registerBean("internet", InternetHealthIndicator.class);
								  })
				.run(args);
	}

	@Bean
	public ApplicationRunner runner(DemoApplicationProperties myApplicationProperties, Environment environment) {
		return args -> {

			List<Address> addresses = Binder.get(environment)
					.bind("demo.addresses", Bindable.listOf(Address.class))
					.orElseThrow(IllegalStateException::new);

			System.out.printf("Demo Addresses : %s\n", addresses);

			// DEMO_ENV_1 Environment Variable
			System.out.printf("Demo Env 1 : %s\n", environment.getProperty("demo.env[1]"));

			System.out.printf("Demo First Name : %s\n", myApplicationProperties.getFirstName());
			System.out.printf("Demo Last Name : %s\n", myApplicationProperties.getLastName());
			System.out.printf("Demo Username : %s\n", myApplicationProperties.getUsername());
			System.out.printf("Demo Working Time (Hours) : %s\n", myApplicationProperties.getWorkingTime().toHours());
			System.out.printf("Demo Number : %d\n", myApplicationProperties.getNumber());
			System.out.printf("Demo Telephone Number : %s\n", myApplicationProperties.getTelephoneNumber());
			System.out.printf("Demo Email 1 : %s\n", myApplicationProperties.getEmailAddresses().get(0));
			System.out.printf("Demo Email 2 : %s\n", myApplicationProperties.getEmailAddresses().get(1));
		};
	}
}
