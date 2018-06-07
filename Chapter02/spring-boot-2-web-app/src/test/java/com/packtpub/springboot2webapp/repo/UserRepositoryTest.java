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
package com.packtpub.springboot2webapp.repo;

import com.packtpub.springboot2webapp.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

/**
 * @author Shazin Sadakath
 */
@RunWith(SpringRunner.class)
    @DataJpaTest
    public class UserRepositoryTest {

        @Autowired
        private TestEntityManager testEntityManager;

        @Autowired
        private UserRepository userRepository;

        @Test
        public void findByUsername_HappyPath_ShouldReturn1User() throws Exception {
            // Given
            User user = new User();
            user.setUsername("shazin");
            user.setPassword("shaz980");
            user.setRole("USER");
            testEntityManager.persist(user);
            testEntityManager.flush();

            // When
            User actual = userRepository.findByUsername("shazin");

            // Then
            assertThat(actual).isEqualTo(user);
        }

    @Test
    public void save_HappyPath_ShouldSave1User() throws Exception {
        // Given
        User user = new User();
        user.setUsername("shazin");
        user.setPassword("shaz980");
        user.setRole("USER");

        // When
        User actual = userRepository.save(user);

        // Then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();
    }


}
