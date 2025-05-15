package com.app.PayTogether.controller;

import com.app.PayTogether.dto.LoginRequestDTO;
import com.app.PayTogether.dto.RegisterRequestDTO;
import com.app.PayTogether.exception.InvalidCredentialsException;
import com.app.PayTogether.servicies.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
        try {
            authService.authenticate(request.getEmail(), request.getPassword());
            return ResponseEntity.ok("Logado com sucesso");
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        authService.register(request);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
}
