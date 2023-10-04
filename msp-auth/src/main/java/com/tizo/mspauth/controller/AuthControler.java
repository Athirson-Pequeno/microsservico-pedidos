package com.tizo.mspauth.controller;

import com.tizo.mspauth.dto.AuthRequest;
import com.tizo.mspauth.entity.User;
import com.tizo.mspauth.repository.UserRepository;
import com.tizo.mspauth.service.AuthService;
import com.tizo.mspauth.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msp-auth")
public class AuthControler {
    @Autowired
    private AuthService authService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register/user")
    public String addNewUser(@RequestBody User user){

        try {
            repositoryService.addNewUser(user);
            return "User created";
        }catch (Exception exception){
            throw new RuntimeException("Error " + exception);
        }

    }
    @PostMapping("/register/admin")
    public String addNewAdmin(@RequestBody User user){

        try {
            repositoryService.addNewAdmin(user);
            return "Admin created";
        }catch (Exception exception){
            throw new RuntimeException("Error " + exception);
        }

    }


    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        User user = userRepository.findByName(authRequest.getUsername()).get();

        if (authenticate.isAuthenticated()) {
            return authService.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }


    @GetMapping("/validate")
    public String getToken(@RequestParam("token") String token){
        authService.validateToken(token);

        return "token is valid";
    }
}
