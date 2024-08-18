package com.example.news_aggregator.service;

import com.example.news_aggregator.exception.DuplicateUsernameException;
import com.example.news_aggregator.exception.InvalidCredentialsException;
import com.example.news_aggregator.exception.InvalidRequestException;
import com.example.news_aggregator.model.PreferencesEnum;
import com.example.news_aggregator.model.User;
import com.example.news_aggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new InvalidCredentialsException("User not found"));;
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new InvalidCredentialsException("User not found"));
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new InvalidCredentialsException("Username or Password is incorrect!!!!");
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password));
//        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));


        Optional<User> ifExitsUser = userRepository.findByUsername(username);
        if(ifExitsUser.isPresent())
                throw new DuplicateUsernameException("Username already exists: " + username);

        return userRepository.save(user);
    }

    public Optional<User>  findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Set<PreferencesEnum> updatePreferences(String username,Set<PreferencesEnum> preferences)
    {
        if(preferences.size()>5) throw new InvalidRequestException("preferences Size must be less or equal to 5");

        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Set<PreferencesEnum>  UserpPreferences = user.getPreferences();
        if(UserpPreferences.isEmpty()) UserpPreferences = preferences;
        else UserpPreferences.addAll(preferences);
        user.setPreferences(UserpPreferences);
        save(user);
        return user.getPreferences();
    }

    public Set<PreferencesEnum> getPreferences(String username)
    {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")).getPreferences();
    }
}

