package com.example.demo.services;

import com.example.demo.model.User;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface UserService {

    void saveUserFromForm(MultiValueMap<String, String> form);

    User getUserByLogin(String login);

    List<User> getAllUsers();

}
