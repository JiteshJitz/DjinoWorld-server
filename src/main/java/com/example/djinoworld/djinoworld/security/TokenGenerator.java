package com.example.djinoworld.djinoworld.security;

import com.example.djinoworld.djinoworld.dto.TokenDTO;
import com.example.djinoworld.djinoworld.model.User;
import com.example.djinoworld.djinoworld.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
public class TokenGenerator {
    @Autowired
    JwtEncoder accessTokenEncoder;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    @Qualifier("jwtRefreshTokenEncoder")
    JwtEncoder refreshTokenEncoder;

    private String createAccessToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("myApp")
                .issuedAt(now)
                .expiresAt(now.plus(25, ChronoUnit.MINUTES))
                .subject(user.getId())
                .build();

        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    private String createAccessToken2(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        User user = optionalUser.get();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("myApp")
                .issuedAt(now)
                .expiresAt(now.plus(25, ChronoUnit.MINUTES))
                .subject(user.getId())
                .build();

        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }


    private String createRefreshToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("myApp")
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.DAYS))
                .subject(user.getId())
                .build();

        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public TokenDTO createToken(Authentication authentication) {

        System.out.println("Principal class: " + authentication.getPrincipal().getClass().getName());

        if (!(authentication.getPrincipal() instanceof User user)) {
            System.out.println("Token inside if");
            System.out.println("Principal class 2: " + authentication.getPrincipal().getClass().getName());

            throw new BadCredentialsException(
                    MessageFormat.format("principal {0} is not of User type", authentication.getPrincipal().getClass())
            );
        }

        System.out.println("Inside create token");

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUserId(user.getId());
        tokenDTO.setAccessToken(createAccessToken(authentication));

        System.out.println("User ID: " + tokenDTO.getUserId());
        System.out.println("Access Token: " + tokenDTO.getAccessToken());

        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {
            Instant now = Instant.now();
            Instant expiresAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expiresAt);
            long daysUntilExpired = duration.toDays();
            if (daysUntilExpired < 7) {
                refreshToken = createRefreshToken(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = createRefreshToken(authentication);
        }
        tokenDTO.setRefreshToken(refreshToken);

        System.out.println("Before if" + tokenDTO);

        return tokenDTO;
    }

    private String createAccessToken3(Authentication authentication) {
        UserDetails userDetails;
        if (authentication.getPrincipal() instanceof Optional) {
            Optional<UserDetails> userDetailsOptional = (Optional<UserDetails>) authentication.getPrincipal();
            userDetails = userDetailsOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));
        } else {
            userDetails = (UserDetails) authentication.getPrincipal();
        }

        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        User user = optionalUser.get();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("myApp")
                .issuedAt(now)
                .expiresAt(now.plus(25, ChronoUnit.MINUTES))
                .subject(user.getId())
                .build();

        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }



    private String createRefreshToken2(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        User user = optionalUser.get();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("myApp")
                .issuedAt(now)
                .expiresAt(now.plus(7, ChronoUnit.DAYS))
                .subject(user.getId())
                .build();

        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    private String createRefreshToken3(Authentication authentication) {
        UserDetails userDetails;
        if (authentication.getPrincipal() instanceof Optional) {
            Optional<UserDetails> userDetailsOptional = (Optional<UserDetails>) authentication.getPrincipal();
            userDetails = userDetailsOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));
        } else {
            userDetails = (UserDetails) authentication.getPrincipal();
        }

        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        User user = optionalUser.get();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("myApp")
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.DAYS))
                .subject(user.getId())
                .build();

        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }



    public TokenDTO createToken2(Authentication authentication, User user) {

        System.out.println("Principal class: " + authentication.getPrincipal().getClass().getName());

        System.out.println("Inside create token");

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUserId(user.getId());
        tokenDTO.setAccessToken(createAccessToken2(authentication));

        System.out.println("User ID: " + tokenDTO.getUserId());
        System.out.println("Access Token: " + tokenDTO.getAccessToken());

        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {
            Instant now = Instant.now();
            Instant expiresAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expiresAt);
            long daysUntilExpired = duration.toDays();
            if (daysUntilExpired < 7) {
                refreshToken = createRefreshToken2(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = createRefreshToken2(authentication);
        }
        tokenDTO.setRefreshToken(refreshToken);

        System.out.println("Before if" + tokenDTO);

        return tokenDTO;
    }

    public TokenDTO createToken3(Authentication authentication, User user) {

        System.out.println("Principal class: " + authentication.getPrincipal().getClass().getName());

        System.out.println("Inside create token");

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUserId(user.getId());
        tokenDTO.setAccessToken(createAccessToken3(authentication));

        System.out.println("User ID: " + tokenDTO.getUserId());
        System.out.println("Access Token: " + tokenDTO.getAccessToken());

        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {
            Instant now = Instant.now();
            Instant expiresAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expiresAt);
            long daysUntilExpired = duration.toDays();
            if (daysUntilExpired < 7) {
                refreshToken = createRefreshToken3(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = createRefreshToken3(authentication);
        }
        tokenDTO.setRefreshToken(refreshToken);

        System.out.println("Before if" + tokenDTO);

        return tokenDTO;
    }

    // For rename
    // Token - Register
    // Token2 - Login
    // Token - Get token back
}