package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User getById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User update(User user, String email, String name, String password) {

        if (user != null) {
            user.setEmail(email);
            user.setName(name);
            user.setPassword(passwordEncoder.encode(password));
        }

        return this.userRepository.save(user);
    }
}