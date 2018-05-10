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
package com.packtpub.springboot2kotlin

import com.packtpub.springboot2kotlin.advanced.Functions
import com.packtpub.springboot2kotlin.gettingstarted.GettingStarted
import com.packtpub.springboot2kotlin.oop.ObjectOrientedProgramming
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringBoot2KotlinApplication {

    @Bean
    fun commandLineRunner(): CommandLineRunner {
        return CommandLineRunner {
            val gettingStarted = GettingStarted();
            gettingStarted.run();
            val oop = ObjectOrientedProgramming();
            oop.run();
            val functions = Functions();
            functions.run();
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringBoot2KotlinApplication>(*args);
}
