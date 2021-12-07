package com.example.demo.repository;

import com.example.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment getPaymentByPaymentId(long paymentId);

    @Query("select p From Payment as p  join Card as c ON p.card_id=c.cardId " +
            "JOIN User as u ON u.userId=c.userId WHERE u.userId=?1 and p.paymentStatus=?2")
    List<Payment> findAllByUserIdAndAndPaymentStatus(long userId, int status);


}
