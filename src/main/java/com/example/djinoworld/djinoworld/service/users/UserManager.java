package com.example.djinoworld.djinoworld.service.users;

import com.example.djinoworld.djinoworld.model.User;
import com.example.djinoworld.djinoworld.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class UserManager implements UserDetailsManager {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void createUser(UserDetails user) {
        ((User) user).setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save((User) user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }


    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        MessageFormat.format("User with email {0} not found", email)
                ));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        MessageFormat.format("username {0} not found", username)
                ));
    }


}
