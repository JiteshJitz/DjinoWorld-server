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
import org.springframework.security.core.GrantedAuthority;
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

import java.util.*;

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
    public ResponseEntity<Map<String, Object>> register(@RequestBody SignupDTO signupDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            UserDetails existingUserByUsername = userManager.loadUserByUsername(signupDTO.getUsername());
            if (existingUserByUsername != null) {
                response.put("status", "error");
                response.put("message", "Username is already in use");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
        } catch (UsernameNotFoundException e) {
            // do nothing if user not found
        }

        try {
            UserDetails existingUserByEmail = userManager.loadUserByEmail(signupDTO.getEmail());
            if (existingUserByEmail != null) {
                response.put("status", "error");
                response.put("message", "Email is already in use");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
        } catch (UsernameNotFoundException e) {
            // do nothing if user not found
        }

        // If username and email do not exist, create new user
        User user = new User(signupDTO.getUsername(), signupDTO.getPassword(), signupDTO.getEmail(),
                signupDTO.getRole(), signupDTO.getFullName(), signupDTO.getLocation());

        userDetailsManager.createUser(user);

        List<GrantedAuthority> authorities = new ArrayList<>(user.getAuthorities());
        System.out.println("authorities" + authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, signupDTO.getPassword(), authorities);

        TokenDTO tokenDTO = tokenGenerator.createToken(authentication);

        response.put("status", "success");
        response.put("accessToken", tokenDTO.getAccessToken());
        response.put("refreshToken", tokenDTO.getRefreshToken());
        return ResponseEntity.ok(response);

    }




    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            Authentication authentication = daoAuthenticationProvider.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUsername(), loginDTO.getPassword()));

            if (authentication == null) {
                throw new CustomException("Authentication object is null.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Retrieve the user from the database
            Optional<User> optionalUser = userRepository.findByUsername(loginDTO.getUsername());
            if (optionalUser.isEmpty()) {
                response.put("status", "error");
                response.put("message", "User not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            User user = optionalUser.get();

            // Create a token with the authentication and user
            TokenDTO tokenDTO = tokenGenerator.createToken2(authentication, user);
            String refreshToken = tokenDTO.getRefreshToken();

            // Set the refresh token in the user object and save it
            user.setRefreshToken(refreshToken);
            userRepository.save(user);

            response.put("status", "success");
            response.put("accessToken", tokenDTO.getAccessToken());
            response.put("refreshToken", tokenDTO.getRefreshToken());
            return ResponseEntity.ok(response);

        } catch (AuthenticationException ex) {
            // Handle specific authentication exceptions
            if (ex instanceof DisabledException) {
                response.put("status", "error");
                response.put("message", "Account is disabled. Please contact support.");
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            } else if (ex instanceof BadCredentialsException) {
                response.put("status", "error");
                response.put("message", "Invalid username or password.");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else {
                response.put("status", "error");
                response.put("message", "Authentication failed.");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        }
    }



    @PostMapping("/token")
    public ResponseEntity token(@RequestBody TokenDTO tokenDTO) {
        Authentication authentication = refreshTokenAuthProvider.authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));
        Jwt jwt = (Jwt) authentication.getCredentials();
        // check if present in db and not revoked,
        System.out.println("Authentication " + authentication);
        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("/token2")
    public ResponseEntity token2(@RequestBody TokenDTO tokenDTO) {
        Authentication authentication = refreshTokenAuthProvider.authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));
        Optional<UserDetails> userDetailsOptional = (Optional<UserDetails>) authentication.getPrincipal();

        // Make sure the Optional<UserDetails> is present
        if(userDetailsOptional.isEmpty()){
            throw new CustomException("User not found in principal.", HttpStatus.NOT_FOUND);
        }

        UserDetails userDetails = userDetailsOptional.get();

        // Fetch the user from the database
        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
        if (optionalUser.isEmpty()) {
            throw new CustomException("User not found.", HttpStatus.NOT_FOUND);
        }
        User user = optionalUser.get();

        // Generate a new token
        TokenDTO newToken = tokenGenerator.createToken3(authentication, user);

        return ResponseEntity.ok(newToken);
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