package edu.co.ustavillavo.noxia.controller;

import edu.co.ustavillavo.noxia.dto.AuthResponse;
import edu.co.ustavillavo.noxia.dto.LoginRequest;
import edu.co.ustavillavo.noxia.dto.RegisterRequest;
import edu.co.ustavillavo.noxia.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registrar(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}