package com.example.demo.controller;

import com.example.demo.model.Card;
import com.example.demo.model.Enums.CardStatus;
import com.example.demo.model.Payment;
import com.example.demo.model.User;
import com.example.demo.services.Impl.CardServiceImpl;
import com.example.demo.services.Impl.PaymentServiceImpl;
import com.example.demo.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class UserController {
    UserServiceImpl userService;
    CardServiceImpl cardService;
    PaymentServiceImpl paymentService;

    public UserController(UserServiceImpl userService,
                          CardServiceImpl cardService, PaymentServiceImpl paymentService) {
        this.userService = userService;
        this.cardService = cardService;
        this.paymentService = paymentService;
    }

    @Autowired


    @GetMapping("/homePage")
    public String userHomePage() {
        return "homePage";
    }

    @PostMapping("/homePage")
    public String userHomePage(@RequestBody MultiValueMap<String, String> formData) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(authUser.getUserLogin());
        System.out.println(user.getPassword());
        System.out.println(formData.get("password"));
        if (formData.get("password").toString()
                .equals(user.getPassword())) {
            cardService.createCard(user);
            return "forward:cardInformation";
        }
        return "homePage";
    }

    @GetMapping("/cardInformation")
    public String userCardInformationPage(Model model) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(authUser.getUserLogin());
        List<Card> cards = cardService.getAllUserCards(user);
        model.addAttribute("cards", cards);
        return "cardInformation";
    }

    @PostMapping("/cardInformation")
    public String userCardInformationPage(HttpServletRequest req, @RequestBody MultiValueMap<String, String> formData) {
        long cardId = Long.parseLong(req.getParameter("hidden"));
        Card card = cardService.searchCardByCardId(cardId);
        System.out.println(cardId);
        if (formData.get("button1") != null && card.getCardStatus().equals(CardStatus.ACTIVE)) {
            cardService.blockCard(cardId);
        } else if (formData.get("button2") != null && card.getCardStatus().equals(CardStatus.BLOCKED)) {
            cardService.requestToUnblock(cardId);
        }
        return "cardInformation";
    }


    @GetMapping("/doPayment")
    public String userDoPaymentPage() {
        return "doPayment";
    }

    @PostMapping("/doPayment")
    public String userDoPaymentPage(HttpServletRequest req) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(authUser.getUserLogin());
        long cardId = Long.parseLong(req.getParameter("userCardId"));
        int paymentSum = Integer.parseInt(req.getParameter("sum"));
        String trType = req.getParameter("trType");
        Card card = cardService.searchCardByCardId(cardId);
        if (card.getUserId() != user.getUserId()) {
            System.out.println("Not user card");
        }
        if (card.getCardStatus().equals(CardStatus.BLOCKED)) {
            System.out.println("Card is blocked");
        } else
            paymentService.createPayment(card, trType, paymentSum);

        return "doPayment";
    }

    @GetMapping("/submitPayment")
    public String userSubmitPaymentPage(Model model) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByLogin(authUser.getUserLogin());
        List<Payment> payments = userService.getUserPayments(user.getUserId(), 0);
        model.addAttribute("payments", payments);
        return "submitPayment";
    }

    @PostMapping("/submitPayment")
    public String userSubmitPaymentPage(HttpServletRequest req) {


        return "submitPayment";
    }

    @GetMapping("/transactionsHistory")
    public String userTransactionHistoryPage() {
        return "transactionsHistory";
    }
}