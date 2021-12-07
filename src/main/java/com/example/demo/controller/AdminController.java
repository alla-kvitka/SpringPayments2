package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.services.Impl.CardServiceImpl;
import com.example.demo.services.Impl.PaymentServiceImpl;
import com.example.demo.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    UserServiceImpl userService;
    CardServiceImpl cardService;
    PaymentServiceImpl paymentService;

    @Autowired
    public AdminController(UserServiceImpl userService,
                          CardServiceImpl cardService, PaymentServiceImpl paymentService) {
        this.userService = userService;
        this.cardService = cardService;
        this.paymentService = paymentService;
    }


    @GetMapping("/adminHomePage")
    public String adminHomePage() {
        return "adminHomePage";
    }

    @GetMapping("/adminAllCards")
    public String adminAllCardsPage() {
        return "adminAllCards";
    }

    @GetMapping("/adminAllUsers")
    public String adminAllUsersPage(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList",userList);
        return "adminAllUsers";
    }


}
