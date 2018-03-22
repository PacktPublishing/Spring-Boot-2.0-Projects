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
package com.packtpub.springboot2intro.properties;

import com.packtpub.springboot2intro.SpringBoot2IntroApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Shazin Sadakath
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBoot2IntroApplication.class)
public class DemoApplicationPropertiesTest {

    @Autowired
    private DemoApplicationProperties demoApplicationProperties;

    @Test
    public void getEmailAddresses_DefaultValueMustBeOverwrittenByValuesInPropertiesFile() {
        //When
        List<String> emailAddresses = demoApplicationProperties.getEmailAddresses();
        //Then
        assertThat(emailAddresses).containsOnly("shazin.sadakath@gmail.com", "shazin.swe@gmail.com");
    }
}
