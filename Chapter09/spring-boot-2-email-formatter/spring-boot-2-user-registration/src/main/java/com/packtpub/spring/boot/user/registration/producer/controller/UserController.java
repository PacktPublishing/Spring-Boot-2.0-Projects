package com.packtpub.spring.boot.user.registration.producer.controller;

import com.packtpub.spring.boot.user.registration.producer.service.AsyncService;
import com.packtpub.spring.boot.user.registration.producer.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AsyncService asyncService;
    private final UserService userService;

    public UserController(AsyncService asyncService, UserService userService) {
        this.asyncService = asyncService;
        this.userService = userService;
    }

    @GetMapping
    public List<String> listUsers() {
        return userService.getAll().stream().map(u -> u.getEmail()).collect(Collectors.toList());
    }

    @RequestMapping("/reset-password/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@PathVariable("email") String email) throws Exception {
        asyncService.sendResetPassword(email);
    }

}
