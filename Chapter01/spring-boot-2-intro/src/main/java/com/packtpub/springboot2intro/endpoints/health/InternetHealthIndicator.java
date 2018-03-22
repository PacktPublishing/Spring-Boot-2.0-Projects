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
package com.packtpub.springboot2intro.endpoints.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Shazin Sadakath
 */
public class InternetHealthIndicator implements HealthIndicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(InternetHealthIndicator.class);

    public static final String UNIVERAL_INTERNET_CONNECTIVITY_CHECKING_URL = "https://www.google.com";

    private final RestTemplate restTemplate;

    public InternetHealthIndicator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Health health() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(UNIVERAL_INTERNET_CONNECTIVITY_CHECKING_URL, String.class);
            LOGGER.info("Internet Health Response Code {}", response.getStatusCode());
            if (response.getStatusCode().is2xxSuccessful()) {
                return Health.up().build();
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred while checking internet connectivity", e);
            return Health.down(e).build();
        }

        return Health.down().build();
    }
}
