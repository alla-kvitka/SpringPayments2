package com.example.demo.services.Impl;

import com.example.demo.model.Card;
import com.example.demo.model.Payment;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final CardRepository cardRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final CardServiceImpl cardService;

    @Override
    public void createPayment(Card card, int paymentSum) {
        card.getPayments().add(Payment.builder()
                .paymentStatus(0)
                .paymentSum(paymentSum)
                .build());
        cardRepository.save(card);
    }


    public Payment getPayment(long paymentId) {
        return paymentRepository.getPaymentByPaymentId(paymentId);
    }

    public void submitPayment(long paymentId) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Payment payment = paymentRepository.getPaymentByPaymentId(paymentId);
        cardService.updateCardBalance(payment.getCard_id(), paymentId);
        payment.setPaymentStatus(1);
        payment.setEventTime(timestamp);
        paymentRepository.save(payment);
    }
}
