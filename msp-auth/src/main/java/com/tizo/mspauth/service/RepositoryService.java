package com.tizo.mspauth.service;

import com.tizo.mspauth.entity.User;
import com.tizo.mspauth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {

    @Autowired
    AuthService authService;

    @Autowired
    RoleRepository roleRepository;

    public void addNewUser(User user){

        user.getRole().add(roleRepository.findById(1L).get());
        authService.saveUser(user);

    }

    public void addNewAdmin(User user){
        user.getRole().add(roleRepository.findById(1L).get());
        user.getRole().add(roleRepository.findById(2L).get());
        authService.saveUser(user);
    }
}
