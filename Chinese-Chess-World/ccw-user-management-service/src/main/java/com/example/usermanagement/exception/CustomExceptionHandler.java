package com.example.usermanagement.exception;

import com.doubleat.ccw.common.dto.response.ResponseError;
import com.doubleat.ccw.usermanagement.exception.EmailHasAlreadyExistsException;
import com.doubleat.ccw.usermanagement.exception.UsernameHasAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * @author Hop Nguyen
 */
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameHasAlreadyExistsException.class)
    public ResponseEntity<ResponseError> handleUsernameHasAlreadyException() {
        ResponseError error = ResponseError.builder()
                .timestamp(new Date(System.currentTimeMillis()))
                .message("Conflict")
                .details("Username has already exits!")
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(EmailHasAlreadyExistsException.class)
    public ResponseEntity<ResponseError> handleEmailHasAlreadyException() {
        ResponseError error = ResponseError.builder()
                .timestamp(new Date(System.currentTimeMillis()))
                .message("Conflict")
                .details("Email has already exits!")
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

}
