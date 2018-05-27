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

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBoot2MovieRatingApplicationTests {

	@Autowired
	lateinit var webTestClient : WebTestClient;

	@Test
	fun `Get Movies - Happy Path`() {
		webTestClient
				.get()
				.uri("/movies")
				.header("Authorization", getBasicAuthorization())
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.json("""[{"id":1,"title":"Titanic","year":1999,"genre":{"name":"Romantic","description":"Romantic"},"cast":[{"name":"Lionardo Dicaprio","inAs":"Jack","noOfAwards":1},{"name":"Kate Winslet","inAs":"Rose","noOfAwards":2}]}]""");
	}

	@Test
	fun `Get Movies - Failure Path (No Authorization)`() {
		webTestClient
				.get()
				.uri("/movies")
				.exchange()
				.expectStatus()
				.isUnauthorized()
	}

	@Test
	fun `Get Movie - Happy Path`() {
		webTestClient
				.get()
				.uri("/movies/1")
				.header("Authorization", getBasicAuthorization())
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.json("""{"id":1,"title":"Titanic","year":1999,"genre":{"name":"Romantic","description":"Romantic"},"cast":[{"name":"Lionardo Dicaprio","inAs":"Jack","noOfAwards":1},{"name":"Kate Winslet","inAs":"Rose","noOfAwards":2}]}""")

	}

	@Test
	fun `Rate Movie - Happy Path`() {
		webTestClient
				.put()
				.uri("/movies/1/rate?comment=Ok Movie&rating=3")
				.header("Authorization", getBasicAuthorization())
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.json("""{"id":1,"title":"Titanic","year":1999,"genre":{"name":"Romantic","description":"Romantic"},"ratings":[{"comment":"Good Movie","rating":5},{"comment":"Ok Movie","rating":3}],"cast":[{"name":"Lionardo Dicaprio","inAs":"Jack","noOfAwards":1},{"name":"Kate Winslet","inAs":"Rose","noOfAwards":2}]}""")
	}

	fun getBasicAuthorization() : String {
		val plainCreds = "user:password"
		val plainCredsBytes = plainCreds.toByteArray()
		val base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		val base64Creds = String(base64CredsBytes)

		return "Basic $base64Creds";
	}

}
