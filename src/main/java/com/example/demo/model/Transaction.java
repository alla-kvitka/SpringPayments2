package com.example.demo.model;

import com.example.demo.model.Enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "transactions")
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trId;

    @Column(name = "tr_date", nullable = false)
    private String date;

    @Column(name = "card_id", nullable = false)
    private String cardId;

    @JoinColumn(name = "payment_sum", nullable = false)
    private int paymentSum;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private TransactionType transactionType;

}