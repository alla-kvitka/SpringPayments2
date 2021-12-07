package com.example.demo.model;

import com.example.demo.model.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_login"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @OneToMany( fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<Card> cards = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_login", nullable = false, unique = true)
    private String userLogin;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_email", nullable = false, unique = true, length = 32)
    private String userEmail;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Column(name = "user_status", nullable = false)
    private String userStatus;
}