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

import com.packtpub.springboot2movierating.model.Movie
import com.packtpub.springboot2movierating.service.MovieService
import org.springframework.web.bind.annotation.*
import reactor.core.Disposable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RequestMapping("/movies")
@RestController
class MovieController constructor(val movieService : MovieService) {

    @GetMapping
    fun getMovies() : Flux<Movie> {
        return this.movieService.findAll();
    }

    @GetMapping("/{id}")
    fun getMovie(@PathVariable id : Int) : Mono<Movie> {
        return this.movieService.findOne(id);
    }

    @PutMapping("/{id}/rate")
    fun rateMovie(@PathVariable id : Int, @RequestParam rating : Int, @RequestParam comment : String) : Mono<Movie> {
        return this.movieService.rate(id, comment, rating);
    }
}
