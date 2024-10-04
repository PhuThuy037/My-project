package com.user_mangement.User_Mangement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserMangementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMangementApplication.class, args);
	}

}
