package com.ek.adminlte.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ek.adminlte.model.User;
import com.ek.adminlte.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<String> apiLogin(@RequestBody LoginRequest request) {
        String token = authenticationService.authenticate(
                request.getUsername(),
                request.getPassword()
        );
        return ResponseEntity.ok(token);
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<User> apiRegister(@Valid @RequestBody User user) {
        return ResponseEntity.ok(authenticationService.register(user));
    }

    @lombok.Data
    static class LoginRequest {
        private String username;
        private String password;
    }
}
