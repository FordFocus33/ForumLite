package com.lite.zero.sugar.forumlite.repository;

import com.lite.zero.sugar.forumlite.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
