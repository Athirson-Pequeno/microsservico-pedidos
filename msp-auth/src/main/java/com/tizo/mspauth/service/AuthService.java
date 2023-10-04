package com.tizo.mspauth.service;

import com.tizo.mspauth.entity.User;
import com.tizo.mspauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JWTService jwtService;

    public String saveUser(User user){

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "User save";
    }

    public String generateToken(String username){

        User user = userRepository.findByName(username).get();

        return jwtService.generateToken(user);

    }

    public void validateToken(String token){

        jwtService.validateToken(token);

    }
}
