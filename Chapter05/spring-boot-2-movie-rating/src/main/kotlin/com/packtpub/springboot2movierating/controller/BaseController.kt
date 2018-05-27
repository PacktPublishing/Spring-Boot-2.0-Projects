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

import com.packtpub.springboot2movierating.dto.ErrorDTO
import com.packtpub.springboot2movierating.exception.MovieNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class BaseController {

    @ExceptionHandler(value = MovieNotFoundException::class)
    fun handleMovieNotFoundException (e : MovieNotFoundException) : ResponseEntity<ErrorDTO> {
        return ResponseEntity<ErrorDTO>(ErrorDTO(400, e.message), HttpStatus.BAD_REQUEST);
    }
}
