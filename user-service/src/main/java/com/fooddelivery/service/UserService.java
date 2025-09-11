package com.fooddelivery.service;

import com.fooddelivery.dto.CreateUserRequestDTO;
import com.fooddelivery.model.User;
import com.fooddelivery.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserRequestDTO requestDTO) {
        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already in use.");
        }
        User newUser = new User();
        newUser.setName(requestDTO.getName());
        newUser.setEmail(requestDTO.getEmail());
        newUser.setPassword(requestDTO.getPassword()); // Remember to hash passwords in a real app!
        newUser.setPhoneNumber(requestDTO.getPhoneNumber());

        userRepository.save(newUser);
    }
}