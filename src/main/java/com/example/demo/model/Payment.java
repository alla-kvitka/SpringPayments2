package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @Column(name = "card_id", updatable = false, insertable = false)
    private long card_id;

    @Column(name = "payment_sum", nullable = false)
    private int paymentSum;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @Column(name = "payment_status", nullable = false)
    private int paymentStatus;

}