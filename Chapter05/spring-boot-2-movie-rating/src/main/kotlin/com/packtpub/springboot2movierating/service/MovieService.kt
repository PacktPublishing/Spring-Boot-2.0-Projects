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
import com.packtpub.springboot2movierating.model.Movie
import com.packtpub.springboot2movierating.model.MovieRating
import com.packtpub.springboot2movierating.repo.MovieRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class MovieService constructor(val movieRepository: MovieRepository) {

    fun findAll() = this.movieRepository.findAll();

    fun save(movie : Movie) : Mono<Movie> = this.movieRepository.save(movie);

    fun findOne(id : Int) : Mono<Movie> = this.movieRepository.findById(id)
            .switchIfEmpty(Mono.error(MovieNotFoundException.create(id)));

    fun rate(id : Int, comment : String, rating : Int) : Mono<Movie> {
        var movieMono: Mono<Movie> = this.findOne(id);
        return movieMono.switchIfEmpty(Mono.error(MovieNotFoundException.create(id))).map({ movie ->
            movie.ratings.add(MovieRating(comment, rating, Date()));
            movie;
        }).map({ movie ->
            this.save(movie).subscribe();
            movie;
        });
    }

}
