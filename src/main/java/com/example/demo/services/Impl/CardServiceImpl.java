package com.example.demo.services.Impl;

import com.example.demo.model.Payment;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.Card;
import com.example.demo.model.Enums.CardStatus;
import com.example.demo.model.Enums.UserRequest;
import com.example.demo.model.User;
import com.example.demo.services.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public void createCard(User user) {
        user.getCards().add(Card.builder()
                .cardStatus(CardStatus.ACTIVE)
                .cardSum(0)
                .unblockRequest(UserRequest.NO_REQUEST)
                .build());
        userRepository.save(user);

    }

    public List<Card> getAllUserCards(User user) {
        return cardRepository.getAllByUserId(user.getUserId());
    }

    @Override
    public Card searchCardByCardId(long cardId) {
        return cardRepository.getCardByCardId(cardId);
    }

    @Override
    public void blockCard(long cardId) {
        Card card = cardRepository.getCardByCardId(cardId);
        card.setCardStatus(CardStatus.BLOCKED);
        cardRepository.save(card);
    }

    @Override
    public void requestToUnblock(long cardId) {
        Card card = cardRepository.getCardByCardId(cardId);
        card.setUnblockRequest(UserRequest.UNBLOCK_CARD);
        cardRepository.save(card);
    }

    @Override
    public void updateCardBalance(long cardId, long paymentId) {
        Card card = cardRepository.getCardByCardId(cardId);
        Payment payment = paymentRepository.getPaymentByPaymentId(paymentId);
        if (payment.getPaymentSum()>=0)
            card.setCardSum(card.getCardSum() + payment.getPaymentSum());
        else
            card.setCardSum(card.getCardSum() - payment.getPaymentSum());
        cardRepository.save(card);
    }

    @Override
    public void unblockCard(long cardId) {
        Card card = cardRepository.getCardByCardId(cardId);
        card.setCardStatus(CardStatus.ACTIVE);
        clearRequestToUnblock(cardId);
        cardRepository.save(card);
    }

    public void clearRequestToUnblock(long cardId) {
        Card card = cardRepository.getCardByCardId(cardId);
        card.setUnblockRequest(UserRequest.NO_REQUEST);
        cardRepository.save(card);
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }
}
