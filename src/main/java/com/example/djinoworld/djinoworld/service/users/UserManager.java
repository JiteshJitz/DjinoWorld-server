package com.example.djinoworld.djinoworld.service.users;

import com.example.djinoworld.djinoworld.dto.*;
import com.example.djinoworld.djinoworld.model.Accommodation;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserDetailsManager {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AccommodationService accommodationService;

    @Autowired
    private CoWorkingSpaceService coWorkingSpaceService;



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

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }



    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        MessageFormat.format("User with email {0} not found", email)
                ));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        MessageFormat.format("username {0} not found", username)
                ));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public UserAccommodationsResponseDTO getUserAndAccommodationInfo(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Accommodation> accommodations = accommodationService.getAccommodationByOwnerID(userId);
        UserResponseDTO userResponseDTO = UserResponseDTO.fromUser(user);
        List<AccommodationResponseDTO> accommodationResponses = accommodations.stream()
                .map(AccommodationResponseDTO::new)
                .collect(Collectors.toList());
        return new UserAccommodationsResponseDTO(userResponseDTO, accommodationResponses);
    }

    public UserCoWorkingSpacesResponseDTO getUserAndCoWorkingSpacesInfo(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserResponseDTO userResponseDTO = UserResponseDTO.fromUser(user);
        List<CoWorkingSpaceResponseDTO> coWorkingSpaceResponseDTOs = coWorkingSpaceService.getCoWorkingSpacesByOwnerID(userId);
        return new UserCoWorkingSpacesResponseDTO(userResponseDTO, coWorkingSpaceResponseDTOs);
    }

}
