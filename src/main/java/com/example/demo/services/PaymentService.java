package com.example.demo.services;

import com.example.demo.model.Card;

public interface PaymentService {

    void createPayment(Card card, String trType, int paymentSum);

}
