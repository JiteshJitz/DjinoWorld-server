package com.example.djinoworld.djinoworld.controller;
import com.example.djinoworld.djinoworld.dto.LoginDTO;
import com.example.djinoworld.djinoworld.dto.LogoutDTO;
import com.example.djinoworld.djinoworld.dto.SignupDTO;
import com.example.djinoworld.djinoworld.dto.TokenDTO;
import com.example.djinoworld.djinoworld.exception.CustomException;
import com.example.djinoworld.djinoworld.model.User;
import com.example.djinoworld.djinoworld.repository.users.UserRepository;
import com.example.djinoworld.djinoworld.security.TokenGenerator;
import com.example.djinoworld.djinoworld.service.users.UserManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserDetailsManager userDetailsManager;

    @Autowired
    UserManager userManager;

    @Autowired
    TokenGenerator tokenGenerator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    @Qualifier("jwtRefreshTokenAuthProvider")
    JwtAuthenticationProvider refreshTokenAuthProvider;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody SignupDTO signupDTO) {
        UserDetails existingUserByUsername = null;
        try {
            existingUserByUsername = userManager.loadUserByUsername(signupDTO.getUsername());
        } catch (UsernameNotFoundException e) {
            // do nothing if user not found
        }

        if (existingUserByUsername != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already in use");
        }

        UserDetails existingUserByEmail = null;
        try {
            existingUserByEmail = userManager.loadUserByEmail(signupDTO.getEmail());
        } catch (UsernameNotFoundException e) {
            // do nothing if user not found
        }

        if (existingUserByEmail != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
        }

        // If username and email do not exist, create new user
        User user = new User(signupDTO.getUsername(), signupDTO.getPassword(), signupDTO.getEmail(), signupDTO.getRole(), signupDTO.getFullName(), signupDTO.getLocation());
        userDetailsManager.createUser(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, signupDTO.getPassword(), Collections.EMPTY_LIST);

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }




    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {

        try {
            Authentication authentication = daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUsername(), loginDTO.getPassword()));
            TokenDTO tokenDTO = tokenGenerator.createToken(authentication);
            String refreshToken = tokenDTO.getRefreshToken();

            // Retrieve the user from the database
            Optional<User> optionalUser = userRepository.findByUsername(loginDTO.getUsername());
            if (optionalUser.isEmpty()) {
                throw new CustomException("User not found.", HttpStatus.NOT_FOUND);
            }
            User user = optionalUser.get();

            // Set the refresh token in the user object
            user.setRefreshToken(refreshToken);

            // Save the user with the refresh token in the database
            userRepository.save(user);

            return ResponseEntity.ok(tokenDTO);

        } catch (AuthenticationException ex) {
            // Handle specific authentication exceptions
            if (ex instanceof DisabledException) {
                // User account is disabled
                throw new CustomException("Account is disabled. Please contact support.", HttpStatus.FORBIDDEN);
            } else if (ex instanceof BadCredentialsException) {
                // Invalid credentials
                throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
            } else {
                // General authentication exception
                throw new CustomException("Authentication failed.", HttpStatus.UNAUTHORIZED);
            }
        }
    }


    @PostMapping("/token")
    public ResponseEntity token(@RequestBody TokenDTO tokenDTO) {
        Authentication authentication = refreshTokenAuthProvider.authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));
        Jwt jwt = (Jwt) authentication.getCredentials();
        // check if present in db and not revoked, etc

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestBody LogoutDTO logoutDTO) {
        // Retrieve the user from the database
        Optional<User> optionalUser = userRepository.findByUsername(logoutDTO.getUsername());
        if (optionalUser.isEmpty()) {
            throw new CustomException("User not found.", HttpStatus.NOT_FOUND);
        }
        User user = optionalUser.get();

        // Clear the refresh token
        user.setRefreshToken(null);

        // Save the user without the refresh token
        userRepository.save(user);

        return ResponseEntity.ok("Logout successful");
    }

}