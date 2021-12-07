package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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

    @Column(name = "payment_status", nullable = false)
    private int paymentStatus;

    @Column(name="startDateTime",columnDefinition="TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date eventTime;
}