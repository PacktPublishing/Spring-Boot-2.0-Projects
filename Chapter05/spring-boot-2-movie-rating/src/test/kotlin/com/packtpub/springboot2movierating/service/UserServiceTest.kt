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

import com.packtpub.springboot2movierating.model.User
import com.packtpub.springboot2movierating.repo.UserRepository
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.test.context.ActiveProfiles
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RunWith(MockitoJUnitRunner::class)
@ActiveProfiles("dev")
class UserServiceTest {

    @Mock
    lateinit var userRepository: UserRepository;

    lateinit var userService: UserService;

    @Before
    fun setup() {
        this.userService = UserService(userRepository);
    }

    @Test
    fun `Saving a User - Happy Path`() {
        // Given
        var user : User = getUser();
        `when`(userRepository.save(user)).thenReturn(Mono.just(user));

        // When
        var actual = userService.save(user);

        // Then
        actual.subscribe({u -> assertThat(user).isEqualTo(u)});
        verify(userRepository, times(1)).save(user);
    }

    @Test
    fun `Find by Username - Happy Path`() {
        // Given
        var user : User = getUser();
        `when`(userRepository.findByUsername("shazin")).thenReturn(Flux.just(user));

        // When
        var actual = userService.getByUsername("shazin");

        // Then
        actual.subscribe({u -> assertThat(user).isEqualTo(u)});
        verify(userRepository, times(1)).findByUsername("shazin");
    }

    fun getUser() : User {
        return User(1, "shazin", "password", "USER", "User of Moviee");
    }

}
