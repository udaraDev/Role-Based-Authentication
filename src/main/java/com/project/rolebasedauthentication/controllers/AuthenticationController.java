package com.project.rolebasedauthentication.controllers;

import com.project.rolebasedauthentication.dtos.LoginResponse;
import com.project.rolebasedauthentication.dtos.LoginUserDto;
import com.project.rolebasedauthentication.dtos.RegisterUserDto;
import com.project.rolebasedauthentication.entities.User;
import com.project.rolebasedauthentication.services.AuthenticationService;
import com.project.rolebasedauthentication.services.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService){
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto){
        logger.info("Signup request received for email: {}", registerUserDto.getEmail());

        try {
            User registeredUser = authenticationService.signup(registerUserDto);
            logger.info("User registered successfully: {}", registeredUser.getEmail());
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            logger.error("Error during signup: ", e);
            return ResponseEntity.status(500).body("Signup failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto){
        logger.info("Login request received for email: {}", loginUserDto.getEmail());

        try {
            User authenticatedUser = authenticationService.authenticate(loginUserDto);
            String jwtToken = jwtService.generateToken(authenticatedUser);
            LoginResponse loginResponse = new LoginResponse()
                    .setToken(jwtToken)
                    .setExpiresIn(jwtService.getJwtExpiration());

            logger.info("User authenticated successfully: {}", authenticatedUser.getEmail());
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            logger.error("Error during login: ", e);
            return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
        }
    }

    // Simple test endpoint
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        logger.info("Test endpoint hit");
        return ResponseEntity.ok("Auth controller is working!");
    }
}