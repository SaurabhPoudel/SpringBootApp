package com.example.firstAppBook.service.impl;

import com.example.firstAppBook.config.JwtUtil;
import com.example.firstAppBook.dto.UserDTO;
import com.example.firstAppBook.dto.UserMapper;
import com.example.firstAppBook.entity.User;
import com.example.firstAppBook.repository.UserRepository;
import com.example.firstAppBook.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    @Transactional
    public String loginWithJwt(String username, String password){
        logger.info("Attempting to login user with username: {}", username);
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            logger.info("User {} logged in successfully", username);
            return jwtUtil.generateToken(username);
        } else {
            logger.warn("Invalid login attempt for user: {}", username);
            throw new RuntimeException("Invalid username or password");
        }

    }

    @Override
    @Transactional
    public UserDTO register(UserDTO userDTO) {
        logger.info("Registering new user: {}", userDTO.getUsername());

        Optional<User> userOpt = userRepository.findByUsername(userDTO.getUsername());
        if (userOpt.isPresent()) {
            logger.warn("Registration failed. User already exists: {}", userDTO.getUsername());

            throw new RuntimeException("User already exists with username: " + userDTO.getUsername());
        }
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        logger.info("User registered successfully: {}", savedUser.getUsername());
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public Optional<UserDTO> findByUsername(String username) {
        logger.debug("Searching for user by username: {}", username);
        return userRepository.findByUsername(username)
                .map(userMapper::toDto);
    }

    @Override
    @Transactional
    public Optional<UserDTO> login(String username, String password){
        logger.info("Login method (non-JWT) called for :{}",username);
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(userMapper::toDto);
    }

}
