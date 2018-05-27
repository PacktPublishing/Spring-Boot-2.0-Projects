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
package com.packtpub.springboot2movierating.controller

import com.packtpub.springboot2movierating.exception.MovieNotFoundException
import com.packtpub.springboot2movierating.model.Actor
import com.packtpub.springboot2movierating.model.Movie
import com.packtpub.springboot2movierating.model.MovieGenre
import com.packtpub.springboot2movierating.model.MovieRating
import com.packtpub.springboot2movierating.repo.MovieRepository
import com.packtpub.springboot2movierating.service.MovieService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@RunWith(SpringRunner::class)
@WebFluxTest(MovieController::class)
@ActiveProfiles("dev")
class MovieControllerTest {

    @MockBean
    lateinit var movieService: MovieService;

    @MockBean
    lateinit var movieRepository: MovieRepository;

    @Autowired
    lateinit var webTestClient: WebTestClient;

    @Test
    fun `List Movies - Happy Path`() {
        // Given
        var movieFlux: Flux<Movie> = Flux.fromIterable(listOf(getMovie()));
        `when`(movieService.findAll()).thenReturn(movieFlux);

        // When
        webTestClient.get().uri("/movies")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("""[{"id":1,"title":"Avengers","year":2018,"genre":{"name":"Action","description":"Action"},"ratings":[],"cast":[]}]""");

        // Then
        verify(movieService, times(1)).findAll();
    }

    @Test
    fun `Rate Movie - Happy Path`() {
        // Given
        var movie = getMovie();
        movie.ratings.add(MovieRating("Great", 5, Date()))
        `when`(movieService.rate(1, "Great", 5)).thenReturn(Mono.just(movie));

        // When
        webTestClient.put().uri("/movies/1/rate?comment=Great&rating=5")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("""{"id":1,"title":"Avengers","year":2018,"genre":{"name":"Action","description":"Action"},"ratings":[{"comment":"Great","rating":5}],"cast":[]}""")

        // Then
        verify(movieService, times(1)).rate(1, "Great", 5)
    }

    @Test
    fun `Rate Movie - Failure Path`() {
        // Given
        `when`(movieService.rate(2, "Great", 5)).thenReturn(Mono.error(MovieNotFoundException.create(2)));

        // When
        webTestClient.put().uri("/movies/2/rate?comment=Great&rating=5")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .json("""{"code":400,"message":"Movie by id 2, not found"}""")

        // Then
        verify(movieService, times(1)).rate(2, "Great", 5)
    }

    @Test
    fun `Get Movie by Id - Happy Path`() {
        // Given
        `when`(movieService.findOne(1)).thenReturn(Mono.just(getMovie()));

        // When
        webTestClient.get().uri("/movies/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("""{"id":1,"title":"Avengers","year":2018,"genre":{"name":"Action","description":"Action"},"ratings":[],"cast":[]}""")

        // Then
        verify(movieService, times(1)).findOne(1);
    }

    fun getMovie() : Movie {
        return Movie(1, "Avengers", 2018, MovieGenre("Action", "Action"), ArrayList<MovieRating>(), ArrayList<Actor>())
    }
}
