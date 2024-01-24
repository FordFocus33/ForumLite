package com.lite.zero.sugar.forumlite.service;

import com.lite.zero.sugar.forumlite.entity.UserEntity;

public interface UserService {

    void save(UserEntity user);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
