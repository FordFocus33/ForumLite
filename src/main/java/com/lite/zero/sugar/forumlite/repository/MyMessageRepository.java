package com.lite.zero.sugar.forumlite.repository;

import com.lite.zero.sugar.forumlite.entity.MyMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyMessageRepository extends JpaRepository<MyMessage, Long> {
    MyMessage findByMessage(String message);
}
