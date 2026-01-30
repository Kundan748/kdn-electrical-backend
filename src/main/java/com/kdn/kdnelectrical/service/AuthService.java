package com.kdn.kdnelectrical.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kdn.kdnelectrical.dto.LoginRequest;
import com.kdn.kdnelectrical.dto.LoginResponse;
import com.kdn.kdnelectrical.entity.User;
import com.kdn.kdnelectrical.exception.InvalidCredentialsException;
import com.kdn.kdnelectrical.repository.UserRepository;
import com.kdn.kdnelectrical.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // =========================
    // LOGIN LOGIC
    // =========================
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid phone or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid phone or password");
        }

        String token = jwtUtil.generateToken(user.getPhone());
        return new LoginResponse(token, user.getRole().name());
    }
}
