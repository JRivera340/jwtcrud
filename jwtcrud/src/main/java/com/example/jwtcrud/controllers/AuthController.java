package com.example.jwtcrud.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtcrud.dto.JwtResponse;
import com.example.jwtcrud.dto.LoginRequest;
import com.example.jwtcrud.dto.RegisterRequest;
import com.example.jwtcrud.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    

    private final AuthService authService;

    @PostMapping("/register")
    public JwtResponse register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/Login")
    public JwtResponse login(@RequestBody LoginRequest request){
        return authService.register(request);
    }

}
