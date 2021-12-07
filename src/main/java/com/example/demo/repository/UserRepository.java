package com.example.demo.repository;


import com.example.demo.model.Enums.Role;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserLogin(String login);

    Optional<User> findByUserLoginAndAndUserRole(String str, Role role);

    List<User> findAll();
}