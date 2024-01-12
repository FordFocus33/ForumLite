package com.lite.zero.sugar.forumlite;

import com.lite.zero.sugar.forumlite.kafka.MessageChangesProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForumLiteApplication {

	@Autowired
	private MessageChangesProducer messageChangesProducer;

	public static void main(String[] args) {
		SpringApplication.run(ForumLiteApplication.class, args);
	}
}
