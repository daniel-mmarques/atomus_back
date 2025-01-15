package com.godev.atomus.controller;

import com.godev.atomus.entity.user.AuthenticationData;
import com.godev.atomus.infra.security.TokenJWTData;
import com.godev.atomus.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.godev.atomus.entity.user.User;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationData authenticationData) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationData.username(), authenticationData.password());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWTData(token));
    }

}
