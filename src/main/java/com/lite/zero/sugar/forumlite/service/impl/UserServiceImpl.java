package com.lite.zero.sugar.forumlite.service.impl;

import com.lite.zero.sugar.forumlite.entity.Role;
import com.lite.zero.sugar.forumlite.entity.UserEntity;
import com.lite.zero.sugar.forumlite.repository.RoleRepository;
import com.lite.zero.sugar.forumlite.repository.UserRepository;
import com.lite.zero.sugar.forumlite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
    public void save(UserEntity user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByName("USER");
        userEntity.setRoles(Collections.singletonList(role));
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
