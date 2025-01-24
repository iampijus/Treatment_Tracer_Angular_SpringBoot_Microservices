package com.healthcare.authentication_service.controller;


import com.healthcare.authentication_service.model.LoginDto;

import com.healthcare.authentication_service.repository.UserCredentialRepository;
import com.healthcare.authentication_service.service.AuthenticationService;
import com.healthcare.authentication_service.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/healthcare/auth")
public class AuthenticationController {

    private JwtService jwtService;
    private AuthenticationService authenticationService;
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService,UserCredentialRepository userCredentialRepository) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.userCredentialRepository=userCredentialRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        boolean isAuthenticated = authenticationService.authenticateUser(loginDto);
        Map<String,String> res=new HashMap<>();
        if (isAuthenticated) {
            String token = jwtService.generateToken(loginDto.getEmail());
            res.put("token",token);
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");

    }

}
