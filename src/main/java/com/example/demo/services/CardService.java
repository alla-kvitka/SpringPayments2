package com.example.demo.services;

import com.example.demo.model.Card;

public interface CardService {

    Card searchCardByCardId(long cardId);

    void blockCard(long cardId);

    void requestToUnblock(long cardId);

    void updateCardBalance(long cardId,long paymentId);

    void unblockCard(long cardId);


}
