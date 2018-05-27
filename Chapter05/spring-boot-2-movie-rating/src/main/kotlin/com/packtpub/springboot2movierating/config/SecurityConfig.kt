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
package com.packtpub.springboot2movierating.config

import com.packtpub.springboot2movierating.service.UserService
import com.packtpub.springboot2movierating.util.MovieeReactiveUserDetailsService
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun securityWebFilterChain(http : ServerHttpSecurity) : SecurityWebFilterChain {
        http.authorizeExchange()
                .pathMatchers("/movies/**")
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();

        return http.build();
    }

    @Bean
    fun authenticationManager(movieeReactiveUserDetailsService: MovieeReactiveUserDetailsService): UserDetailsRepositoryReactiveAuthenticationManager {
        val userDetailsRepositoryReactiveAuthenticationManager = UserDetailsRepositoryReactiveAuthenticationManager(movieeReactiveUserDetailsService)
        userDetailsRepositoryReactiveAuthenticationManager.setPasswordEncoder(passwordEncoder())

        return userDetailsRepositoryReactiveAuthenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Profile("default")
    fun applicationRunner(userService : UserService): ApplicationRunner {
        return ApplicationRunner {
            userService.save(com.packtpub.springboot2movierating.model.User(1, "user", passwordEncoder().encode("password"), "USER", "User of Moviee")).subscribe();
            userService.save(com.packtpub.springboot2movierating.model.User(2, "admin", passwordEncoder().encode("password"), "ADMIN", "Admin of Moviee")).subscribe()
        }
    }

}
