package com.packtpub.springboot2blog.controller;

import com.packtpub.springboot2blog.service.ArticleService;
import com.packtpub.springboot2blog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest(ArticleController.class)
public class ArticleControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @MockBean
    private ArticleService articleService;

    @Test
    public void getNewArticlePage_ShouldReturnNewArticlePage() {
        //ReactiveSecurityContextHolder.withAuthentication(new UsernamePasswordAuthenticationToken("user", "N/A"));
        webTestClient.get().uri("/article/new").accept(MediaType.TEXT_HTML).exchange().expectStatus().isOk();
    }
}
