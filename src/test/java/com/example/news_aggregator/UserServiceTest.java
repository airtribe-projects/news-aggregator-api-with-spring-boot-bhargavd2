package com.example.news_aggregator;


import com.example.news_aggregator.dto.UserDto;
import com.example.news_aggregator.model.User;
import com.example.news_aggregator.repository.UserRepository;
import com.example.news_aggregator.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testRegisterUser_Success() {
        String username = "user321";
        String password = "password321";
        String encodedPassword = "encodedPassword";
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registeredUser = userService.registerUser(username, password);

        assertNotNull(registeredUser);
        assertEquals(username, registeredUser.getUsername());
        assertEquals(encodedPassword, registeredUser.getPassword());
        verify(userRepository).findByUsername(username);
        verify(passwordEncoder).encode(password);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testRegisterUser_UserAlreadyExists() {
        // Given
        String username = "user";
        String password = "password";
        User existingUser = new User();
        existingUser.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));

        // When & Then
        RuntimeException thrown = assertThrows(RuntimeException.class, () ->
                        userService.registerUser(username, password),
                "Expected registerUser() to throw, but it didn't");

        assertEquals("Username already exists: " + username, thrown.getMessage());
        verify(userRepository).findByUsername(username);
        verify(passwordEncoder, never()).encode(password);
        verify(userRepository, never()).save(any(User.class));
    }



}
