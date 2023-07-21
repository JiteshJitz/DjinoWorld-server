package com.example.djinoworld.djinoworld.security;

import com.example.djinoworld.djinoworld.model.User;
import com.example.djinoworld.djinoworld.model.users.Nomad;
import com.example.djinoworld.djinoworld.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        System.out.println("jwt"+ jwt);
        //System.out.println("JWT Claims: " + jwt.getClaims());

        String userId = jwt.getSubject();
        Optional<User> user = userRepository.findById(userId); // assuming you have a method like this in userRepository

        System.out.println("user auth "+ user);
        if (user.isPresent()) {
            System.out.println("hello found ");
            return new UsernamePasswordAuthenticationToken(user, jwt, user.get().getAuthorities());
        } else {
            System.out.println("hello not found ");
            // Handle case where user is not present
            return (UsernamePasswordAuthenticationToken) user.orElseThrow(() -> new UsernameNotFoundException("User not found")).getAuthorities();

        }


    }

}