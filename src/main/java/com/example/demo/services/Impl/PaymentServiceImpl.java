package com.example.demo.services.Impl;

import com.example.demo.model.Card;
import com.example.demo.model.Payment;
import com.example.demo.model.User;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final CardRepository cardRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Override
    public void createPayment(Card card, String trType, int paymentSum) {
        User user = userRepository.getById(card.getUserId());
        card.getPayments().add(Payment.builder()
                .paymentStatus(0)
                .paymentSum(paymentSum)
                .paymentType(trType)
                .build());
        cardRepository.save(card);
    }


}
