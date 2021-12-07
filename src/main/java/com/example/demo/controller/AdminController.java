package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.model.Enums.CardStatus;
import com.example.demo.model.Enums.UserStatus;
import com.example.demo.model.User;
import com.example.demo.services.Impl.CardServiceImpl;
import com.example.demo.services.Impl.PaymentServiceImpl;
import com.example.demo.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
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
    public String adminAllCardsPage(Model model) {
        List<Card> cardList = cardService.getAllCards();
        model.addAttribute("cardList", cardList);
        return "adminAllCards";
    }

    @PostMapping("/adminAllCards")
    public void adminAllCardsPage(HttpServletRequest req, @RequestBody MultiValueMap<String, String> formData){
        long cardId = Long.parseLong(req.getParameter("hidden"));
        Card card = cardService.searchCardByCardId(cardId);
        if (formData.get("button3") != null && card.getCardStatus().equals(CardStatus.ACTIVE)) {
            cardService.blockCard(cardId);
        } else if (formData.get("button4") != null && card.getCardStatus().equals(CardStatus.BLOCKED)) {
            cardService.unblockCard(cardId);
        }
    }

    @GetMapping("/adminAllUsers")
    public String adminAllUsersPage(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "adminAllUsers";
    }

    @PostMapping("/adminAllUsers")
    public void adminAllUsersPage(HttpServletRequest req, @RequestBody MultiValueMap<String, String> formData){
        long userId = Long.parseLong(req.getParameter("hidden"));
        User user = userService.getUserById(userId);
        if (formData.get("button3") != null) {
            userService.blockUser(userId);
        } else if (formData.get("button4") != null ) {
            userService.unblockUser(userId);
        }
    }

}
