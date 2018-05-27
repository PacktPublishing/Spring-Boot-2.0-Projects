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
package com.packtpub.springboot2movierating

import com.packtpub.springboot2movierating.model.Actor
import com.packtpub.springboot2movierating.model.Movie
import com.packtpub.springboot2movierating.model.MovieGenre
import com.packtpub.springboot2movierating.model.MovieRating
import com.packtpub.springboot2movierating.repo.MovieRepository
import com.packtpub.springboot2movierating.service.MovieService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import reactor.core.publisher.Mono
import java.util.*

@SpringBootApplication
class SpringBoot2MovieRatingApplication {

    @Bean
    @Profile("default")
    fun commandLineRunner(movieService: MovieService) : CommandLineRunner {
        return CommandLineRunner {
            var movie : Movie = Movie(1, "Titanic", 1999, MovieGenre("Romantic", "Romantic"), Arrays.asList(MovieRating("Good Movie", 5, Date())), listOf(Actor("Lionardo Dicaprio", "Jack", 1), Actor("Kate Winslet", "Rose", 2)));
            Mono.just(movie).subscribe({movie -> movieService.save(movie).subscribe();});
        };
    }
}

fun main(args: Array<String>) {
    runApplication<SpringBoot2MovieRatingApplication>(*args)
}
