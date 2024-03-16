package com.mohanad.springpractice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("login")
    public String login() {
        return "auth/login-page";
    }

    @GetMapping("access-denied")
    public String accessDenied() {
        return "auth/access-denied";
    }
}
