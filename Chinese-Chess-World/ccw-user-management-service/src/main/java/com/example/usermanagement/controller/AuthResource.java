package com.example.usermanagement.controller;

import com.doubleat.ccw.common.security.SecurityUtils;
import com.doubleat.ccw.usermanagement.controller.AuthController;
import com.doubleat.ccw.usermanagement.dto.UserDto;
import com.doubleat.ccw.usermanagement.dto.request.LoginRequest;
import com.doubleat.ccw.usermanagement.dto.request.SignupRequest;
import com.doubleat.ccw.usermanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Hop Nguyen
 */
@RestController
public class AuthResource implements AuthController {

    private final AuthService authService;

    @Autowired
    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String token = authService.authenticateUser(loginRequest);

        HttpCookie cookie = SecurityUtils.createAuthCookie(token, 604800L);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok()
                .headers(headers)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<?> validateAccessToken(@CookieValue(name = "access_token", required = false) String accessToken) {
        Optional<UserDto> userDtoOptional = authService.validateAccessToken(accessToken);

        if (userDtoOptional.isPresent())
            return ResponseEntity.ok(userDtoOptional.get());
        else
            return ResponseEntity.badRequest().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        authService.signupUser(signupRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

