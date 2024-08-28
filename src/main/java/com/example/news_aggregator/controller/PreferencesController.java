package com.example.news_aggregator.controller;

import com.example.news_aggregator.model.PreferencesEnum;
import com.example.news_aggregator.model.User;
import com.example.news_aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class PreferencesController {

    @Autowired
    private UserService userService;

    @GetMapping("/preferences")
    public ResponseEntity<?> getPreferences(Authentication authentication) {
        Map<String, Set<PreferencesEnum>> response = new HashMap<>();
        response.put("userPreference",userService.getPreferences(authentication.getName()));
        response.put("avaliablePreferences",new HashSet<>(Arrays.asList(PreferencesEnum.values())));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/preferences")
    public ResponseEntity<?> updatePreferences(@RequestBody Set<PreferencesEnum> preferences, Authentication authentication) {
        return ResponseEntity.ok(userService.updatePreferences(authentication.getName(),preferences));
    }


}

