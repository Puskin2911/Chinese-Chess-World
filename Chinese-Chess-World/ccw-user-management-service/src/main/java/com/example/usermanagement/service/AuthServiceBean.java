package com.example.usermanagement.service;

import com.doubleat.ccw.usermanagement.dto.UserDto;
import com.doubleat.ccw.usermanagement.dto.request.LoginRequest;
import com.doubleat.ccw.usermanagement.dto.request.SignupRequest;
import com.doubleat.ccw.usermanagement.exception.EmailHasAlreadyExistsException;
import com.doubleat.ccw.usermanagement.exception.UsernameHasAlreadyExistsException;
import com.doubleat.ccw.usermanagement.service.AuthService;
import com.example.usermanagement.domain.User;
import com.example.usermanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Hop Nguyen
 */
@Service
public class AuthServiceBean implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    public String authenticateUser(LoginRequest loginRequest) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserDto> validateAccessToken(String accessToken) {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signupUser(SignupRequest signupRequest) {
        // 1. Validate Email
        String email = signupRequest.getEmail();
        boolean existsByEmail = userRepository.existsByEmail(email);
        if (existsByEmail) {
            LOGGER.warn("Email has already exists! Cannot sign up user by email {}", email);
            throw new EmailHasAlreadyExistsException();
        }

        // 2. Validate username
        String username = signupRequest.getUsername();
        boolean existsByUsername = userRepository.existsByUsername(username);
        if (existsByUsername) {
            LOGGER.warn("Username has already exists! Cannot sign up user by username {}", email);
            throw new UsernameHasAlreadyExistsException();
        }

        // 3. Process create new user
        String passHashed = passwordEncoder.encode(signupRequest.getPassword());

        User user = User.builder()
                .username(username)
                .email(email)
                .passHashed(passHashed)
                .build();
        userRepository.save(user);
        LOGGER.info("New user has been created!");
    }

}
