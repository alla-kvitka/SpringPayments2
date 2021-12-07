package com.example.demo.model;

import com.example.demo.model.Enums.CardStatus;
import com.example.demo.model.Enums.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards")
public class Card {
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "card_id", referencedColumnName = "card_id")
    private List<Payment> payments = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", nullable = false)
    private long cardId;

    @Column(name = "user_id", updatable = false, insertable = false)
    private long userId;

    @Column(name = "card_sum", nullable = false)
    private long cardSum;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_status")
    private CardStatus cardStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "unblock_request", nullable = false)
    private UserRequest unblockRequest;


}
