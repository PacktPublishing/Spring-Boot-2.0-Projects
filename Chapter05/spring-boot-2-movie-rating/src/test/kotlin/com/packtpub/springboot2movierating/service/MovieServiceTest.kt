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
package com.packtpub.springboot2movierating.service

import com.packtpub.springboot2movierating.exception.MovieNotFoundException
import com.packtpub.springboot2movierating.model.Actor
import com.packtpub.springboot2movierating.model.Movie
import com.packtpub.springboot2movierating.model.MovieGenre
import com.packtpub.springboot2movierating.model.MovieRating
import com.packtpub.springboot2movierating.repo.MovieRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.test.context.ActiveProfiles
import reactor.core.publisher.Mono
import java.util.*

@RunWith(MockitoJUnitRunner::class)
@ActiveProfiles("dev")
class MovieServiceTest {

    @Mock
    lateinit var movieRepository: MovieRepository;

    lateinit var movieService: MovieService;

    @Before
    fun setup() {
        movieService = MovieService(movieRepository);
    }

    @Test
    fun `Saving a Movie - Happy Path`() {
        // Given
        var expected = getMovie();
        `when`(movieRepository.save(expected)).thenReturn(Mono.just(expected));

        // When
        val actual = movieService.save(expected);

        // Then
        actual.subscribe({movie -> assertThat(movie).isEqualTo(expected)});
        verify(movieRepository, times(1)).save(expected);
    }

    @Test
    fun `Find a Movie by Id - Happy Path`() {
        // Given
        var expected = getMovie();
        `when`(movieRepository.findById(1)).thenReturn(Mono.just(expected));

        // When
        val actual = movieService.findOne(1);

        // Then
        actual.subscribe({movie -> assertThat(movie).isEqualTo(expected)});
        verify(movieRepository, times(1)).findById(1);
    }

    @Test
    fun `Find a Movie by Id - Failure Path`() {
        // Given
        var expected = getMovie();
        `when`(movieRepository.findById(1)).thenReturn(Mono.empty());

        // When
        val actual = movieService.findOne(1);

        // Then
        actual.doOnError({t -> assertThat(t).isInstanceOf(MovieNotFoundException::class.java)}).subscribe();
        verify(movieRepository, times(1)).findById(1);
    }

    @Test
    fun `Rate a Movie - Happy Path`() {
        // Given
        var expected = getMovie();
        `when`(movieRepository.findById(1)).thenReturn(Mono.just(expected));
        `when`(movieRepository.save(expected)).thenReturn(Mono.just(expected));

        // When
        var actual = movieService.rate(1, "Great", 5);

        // Then
        actual.subscribe({movie -> assertThat(movie.ratings).hasSize(1)});
        verify(movieRepository, times(1)).findById(1);
        verify(movieRepository, times(1)).save(expected);
    }

    @Test
    fun `Rate a Movie - Failure Path`() {
        // Given
        `when`(movieRepository.findById(1)).thenReturn(Mono.empty());

        // When
        var actual = movieService.rate(1, "Great", 5);

        // Then
        actual.doOnError({t -> assertThat(t).isInstanceOf(MovieNotFoundException::class.java)}).subscribe();
    }

    fun getMovie() : Movie {
        return Movie(1, "Avengers", 2018, MovieGenre("Action", "Action"), ArrayList<MovieRating>(), ArrayList<Actor>())
    }


}
