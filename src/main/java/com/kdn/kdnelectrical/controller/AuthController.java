package com.kdn.kdnelectrical.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kdn.kdnelectrical.dto.LoginRequest;
import com.kdn.kdnelectrical.dto.LoginResponse;
import com.kdn.kdnelectrical.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    	 System.out.println("🔥 LOGIN API HIT 🔥");
        return ResponseEntity.ok(authService.login(request));
    }
}
