package com.rungroup.web.service.impl;

import com.rungroup.web.dto.RegistrationDto;
import com.rungroup.web.models.Role;
import com.rungroup.web.models.UserEntity;
import com.rungroup.web.repository.RoleRepository;
import com.rungroup.web.repository.UserRepository;
import com.rungroup.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername()); /* Mappping DTO to User for db save */
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        Role role = roleRepository.findByName("USER"); /* Every new User will have "USER" privileges */
        user.setRoles(Arrays.asList(role));

        userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public UserEntity findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        return user;
    }
}
