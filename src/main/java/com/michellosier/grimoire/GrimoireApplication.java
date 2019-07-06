package com.michellosier.grimoire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GrimoireApplication {
	public static void main(String[] args) {
		SpringApplication.run(GrimoireApplication.class, args);
	}

}
