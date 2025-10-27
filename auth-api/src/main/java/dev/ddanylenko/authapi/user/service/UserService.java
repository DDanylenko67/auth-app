package dev.ddanylenko.authapi.user.service;

import dev.ddanylenko.authapi.config.jwt.JwtService;
import dev.ddanylenko.authapi.user.model.UserDto;
import dev.ddanylenko.authapi.user.model.UserEntity;
import dev.ddanylenko.authapi.user.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public void register(UserDto userDto) {
        logger.info("Registering user: {}", userDto.getEmail());
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new EntityExistsException("User already exists");
        }
        UserEntity user = new UserEntity(null, userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    public String login(UserDto userDto) {
        logger.info("Login user: {}", userDto.getEmail());
        UserEntity userEntity = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if(userEntity == null){
            throw new EntityNotFoundException("User are not found with email" + userDto.getEmail());
        }
        if(!passwordEncoder.matches(userDto.getPassword(), userEntity.getPasswordHash())){
            throw new IllegalArgumentException("Wrong password!");
        }
        return jwtService.generateToken(userEntity.getEmail());
    }

    public UUID getUserId(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        if(userEntity == null){
            throw new EntityNotFoundException("User are not found with email" + email);
        }
        return userEntity.getId();
    }
}
