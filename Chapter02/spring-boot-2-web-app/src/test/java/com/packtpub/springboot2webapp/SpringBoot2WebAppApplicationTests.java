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
package com.packtpub.springboot2webapp;

import com.packtpub.springboot2webapp.model.Comment;
import com.packtpub.springboot2webapp.model.CommentType;
import com.packtpub.springboot2webapp.repo.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Shazin Sadakath
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBoot2WebAppApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CommentRepository commentRepository;

    @Test
    public void saveComments_HappyPath_ShouldReturnStatus302() throws Exception {
        // When
        ResultActions resultActions = mockMvc.perform(post("/comment").with(csrf()).with(user("shazin").roles("USER")).requestAttr("plusComment", "Test Plus"));

        // Then
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"));
    }

	@Test
	public void getComments_HappyPath_ShouldReturnStatus200() throws Exception {
		// Given
		Comment comment = new Comment();
		comment.setComment("Test Comment");
		comment.setType(CommentType.PLUS);
		comment.setCreatedBy("shazin");
		commentRepository.save(comment);

		// When
		ResultActions resultActions = mockMvc.perform(get("/").with(user("shazin").roles("USER")));

		// Then
		resultActions
				.andExpect(status().isOk())
				.andExpect(view().name("comment"))
				.andExpect(model().attribute("plusComments", hasSize(1)))
				.andExpect(model().attribute("plusComments", hasItem(
						allOf(
								hasProperty("createdBy", is("shazin")),
								hasProperty("comment", is("Test Comment"))
						)
				)));

	}
}
