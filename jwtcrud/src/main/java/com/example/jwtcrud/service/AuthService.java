package com.example.jwtcrud.service;



import javax.management.RuntimeErrorException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.jwtcrud.dto.*;
import com.example.jwtcrud.dto.JwtResponse;
import com.example.jwtcrud.model.Usuario;
import com.example.jwtcrud.repository.UsuarioRepository;
import com.example.jwtcrud.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtutil;




    public JwtResponse register(RegisterRequest registerRequest){
        Usuario user = Usuario.builder().username(request.getUserName())
        .password(passwordEncoder.encode(request.getPassword()))
        .role("USER")
        .build();

        usuarioRepository.save(user);

        String token = JwtUtil.generateToken(user);

        return new JwtResponse(token);
    }


    public JwtResponse login(LoginRequest loginRequest){
        Usuario user = usuarioRepository.findByUsername(request.getUserName()).orElseThrow(() -> new RuntimeException("Error al logear"));


        if (!passwordEncoder.matches(request.getUserName(), user.getPassword())) {

            throw new RuntimeException("Contraseña no valida");
            
        }

        String token = JwtUtil.generateToken(user);
        return JwtResponse(token);
    }
}
