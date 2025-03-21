package com.ce.um.gestion_usuarios.controller;


import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ce.um.gestion_usuarios.config.JwtUtil;
import com.ce.um.gestion_usuarios.model.dto.LoginDto;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwUtil;

    
    @Autowired
    public AuthController(AuthenticationManager authenticationManager,JwtUtil jwUtil) {
        this.authenticationManager = authenticationManager;
        this.jwUtil = jwUtil;
    }



    @PostMapping("/login")
    public ResponseEntity <Void> login(@RequestBody LoginDto loginDto){

        try {
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());


            Authentication authentication = this.authenticationManager.authenticate(login);

            System.out.println(authentication.isAuthenticated());
            System.out.println(authentication.getPrincipal());

            String jwt = this.jwUtil.create(loginDto.getUsername());

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwt).build();

            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }



    }

}
