package com.logistics.moolryu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MoolRyuApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoolRyuApplication.class, args);
	}

}
