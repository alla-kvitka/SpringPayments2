package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "transactions")
@Data
@Entity
@NoArgsConstructor

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trId;

//    @JoinColumn(name = "userId", nullable = false)
//    private int userId;
//
//    @JoinColumn(name = "bill_id", nullable = false)
//    private long billId;
//
//    @JoinColumn(name = "cardId", nullable = false)
//    private int cardId;

    @Column(name = "tr_date", nullable = false)
    private String date;

    @JoinColumn(name = "payment_sum", nullable = false)
    private int paymentSum;

    @Column(name = "payment_type", nullable = false)
    private String transactionType;

    @Column(name = "transaction_status", nullable = false)
    private int status;
}