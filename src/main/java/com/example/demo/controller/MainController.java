package com.example.demo.controller;

import com.example.demo.model.Enums.Role;
import com.example.demo.model.User;
import com.example.demo.services.Impl.CardServiceImpl;
import com.example.demo.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    UserServiceImpl userService;
    CardServiceImpl cardService;

    @Autowired
    public MainController(UserServiceImpl userService, CardServiceImpl cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@RequestBody MultiValueMap<String, String> formData) {
        userService.saveUserFromForm(formData);
        cardService.createCard(userService.getUserByLogin(formData.get("login").toString()));
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPage(@RequestBody MultiValueMap<String, String> formData) {
        User user = userService.getUserByLogin(formData.get("login").toString());
        if (formData.get("password").toString()
                .equals(user.getPassword())) {
            SecurityContextHolder.setContext(new SecurityContextImpl());
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority(user.getUserRole().name())));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (user.getUserRole() == Role.USER) {
                return "homePage";
            } else
                return "adminHomePage";
        }
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }
}
