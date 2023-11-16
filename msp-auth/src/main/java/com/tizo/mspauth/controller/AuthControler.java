package com.tizo.mspauth.controller;

import com.tizo.mspauth.dto.AuthRequest;
import com.tizo.mspauth.entity.User;
import com.tizo.mspauth.repository.UserRepository;
import com.tizo.mspauth.service.AuthService;
import com.tizo.mspauth.service.JWTService;
import com.tizo.mspauth.service.RepositoryService;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    RepositoryService repositoryService;

    @Autowired
    JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register/user")
    public ResponseEntity<String> addNewUser(@RequestBody User user){

        try {
            repositoryService.addNewUser(user);
            return ResponseEntity.ok("User created");
        }catch (DataIntegrityViolationException exception){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("User already exist" );
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating user" );
        }

    }
    @PostMapping("/register/admin")
    public ResponseEntity<String> addNewAdmin(@RequestBody User user){

        try {
            repositoryService.addNewAdmin(user);
            return ResponseEntity.ok("Admin created");
        }catch (DataIntegrityViolationException exception){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Admin already exist" );
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating admin" );
        }

    }


    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest){

        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            if (authenticate.isAuthenticated()) {
                return ResponseEntity.ok(authService.generateToken(authRequest.getEmail()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid access, bad credentials");
        }

    }


    @GetMapping("/validate")
    public ResponseEntity<String> getToken(@RequestHeader String authorization){

        try {
            String newToken = authorization.replace("Bearer ", "");
            authService.validateToken(newToken);
            return ResponseEntity.ok("Token is valid, credentials: " + jwtService.getRoles(newToken));

        }catch (MalformedJwtException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
}
