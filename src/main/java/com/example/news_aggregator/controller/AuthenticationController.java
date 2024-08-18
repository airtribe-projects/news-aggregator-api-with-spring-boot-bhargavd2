package com.example.news_aggregator.controller;

import com.example.news_aggregator.dto.UserDto;
import com.example.news_aggregator.securityConfig.JwtTokenUtil;
import com.example.news_aggregator.model.User;
import com.example.news_aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        System.out.println("try");
        User user = userService.registerUser(userDto.getUsername(), userDto.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/login")
    public ResponseEntity<?> test()
    {
        return ResponseEntity.ok("make a post call");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {

        User user = userService.authenticateUser(userDto.getUsername(), userDto.getPassword());

        String token = jwtTokenUtil.generateToken(userDto);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}

