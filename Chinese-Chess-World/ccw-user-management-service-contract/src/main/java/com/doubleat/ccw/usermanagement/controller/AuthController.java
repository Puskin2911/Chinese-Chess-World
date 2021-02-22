package com.doubleat.ccw.usermanagement.controller;

import com.doubleat.ccw.common.constant.ApiResourceConstant;
import com.doubleat.ccw.common.controller.BaseController;
import com.doubleat.ccw.usermanagement.dto.request.LoginRequest;
import com.doubleat.ccw.usermanagement.dto.request.SignupRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(value = "Auth Resources", tags = "Authentication")
@RequestMapping(value = AuthController.ROOT_API)
public interface AuthController extends BaseController {

    String AUTH_RESOURCE = "/auth";
    String ROOT_API = PUBLIC_API + VERSION_V1 + ApiResourceConstant.USER_MANAGEMENT_RESOURCE + AUTH_RESOURCE;

    String LOGIN_RESOURCE = "/login";
    String VALIDATE_RESOURCE = "/validate";
    String SIGNUP_RESOURCE = "/signup";

    @ApiOperation(value = "Authenticate user with username and password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Authenticate user successfully"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping(value = LOGIN_RESOURCE)
    ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest);

    @ApiOperation(value = "Validate user's access token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Validate access token successfully"),
            @ApiResponse(code = 400, message = "Invalid access token")
    })
    @GetMapping(value = VALIDATE_RESOURCE)
    ResponseEntity<?> validateAccessToken(@CookieValue(name = "access_token", required = false) String accessToken);

    @ApiOperation(value = "Sign up one user to system")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 409, message = "User has already exists")
    })
    @PostMapping(value = SIGNUP_RESOURCE)
    ResponseEntity<?> signupUser(@RequestBody @Valid SignupRequest signupRequest);

}
