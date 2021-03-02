package com.doubleat.ccw.usermanagement.service;

import com.doubleat.ccw.usermanagement.dto.UserDto;
import com.doubleat.ccw.usermanagement.dto.request.LoginRequest;
import com.doubleat.ccw.usermanagement.dto.request.SignupRequest;
import com.doubleat.ccw.usermanagement.exception.EmailHasAlreadyExistsException;
import com.doubleat.ccw.usermanagement.exception.UsernameHasAlreadyExistsException;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Optional;

public interface AuthService {

    /**
     * Authenticate user and return access token.
     *
     * @param loginRequest the object contain username and password for authentication.
     * @return access token if username and password is valid.
     * @throws BadCredentialsException if username or password incorrect.
     */
    String authenticateUser(LoginRequest loginRequest);

    /**
     * Validate access token.
     *
     * @param accessToken the token need to validate.
     * @return {@code Optional<UserDto>} if token is valid. Other wise, return {@code Optional.empty()}.
     */
    Optional<UserDto> validateAccessToken(String accessToken);

    /**
     * Signup new user.
     *
     * @param signupRequest {@code SignupRequest} the object contains information for authentication.
     * @throws UsernameHasAlreadyExistsException if username has already exists.
     * @throws EmailHasAlreadyExistsException    if email has already exists.
     */
    void signupUser(SignupRequest signupRequest);

}
