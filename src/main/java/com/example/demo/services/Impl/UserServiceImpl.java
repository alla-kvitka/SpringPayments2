package com.example.demo.services.Impl;

import com.example.demo.model.Payment;
import com.example.demo.model.User;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.example.demo.model.Enums.Role.USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public void saveUserFromForm(MultiValueMap<String, String> form) {
        userRepository.save(User.builder()
                .userLogin(form.get("login").toString())
                .userEmail(form.get("email").toString())
                .password(form.get("password").toString())
                .userStatus("ACTIVE")
                .userRole(USER)
                .build());
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByUserLogin(login);
    }

    public List<Payment> getUserPayments(Long userId, int status) {
        return paymentRepository.findAllByUserIdAndAndPaymentStatus(userId, status);
    }

}
