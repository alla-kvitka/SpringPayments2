package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/adminHomePage")
    public String adminHomePage() {
        return "adminHomePage";
    }

    @GetMapping("/adminAllCards")
    public String adminAllCardsPage() {
        return "adminAllCards";
    }

    @GetMapping("/adminAllUsers")
    public String adminAllUsersPage() {
        return "adminAllUsers";
    }
}
