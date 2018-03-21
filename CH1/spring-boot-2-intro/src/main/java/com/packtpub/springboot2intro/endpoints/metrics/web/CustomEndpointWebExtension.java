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
package com.packtpub.springboot2intro.endpoints.metrics.web;

import com.packtpub.springboot2intro.endpoints.custom.CustomEndpoint;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.stereotype.Component;

/**
 * @author Shazin Sadakath
 */
@Component
@EndpointWebExtension(endpoint = CustomEndpoint.class)
public class CustomEndpointWebExtension {

    public static final String CUSTOM_ENDPOINT_CALLS = "custom.endpoint.calls";

    private final MeterRegistry meterRegistry;

    public CustomEndpointWebExtension(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @ReadOperation
    public WebEndpointResponse<String> getWeb() {
        meterRegistry.counter(CUSTOM_ENDPOINT_CALLS).increment();
        return new WebEndpointResponse<>("Custom Web Extension Hello, World!", 200);
    }
}
