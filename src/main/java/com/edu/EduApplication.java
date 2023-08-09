package com.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.edu.dao.AdminRepository;
import com.edu.dao.ProfileRepository;
import com.edu.dao.UserRepository;

@SpringBootApplication
public class EduApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(EduApplication.class, args);

		UserRepository userRepo = context.getBean(UserRepository.class);
		ProfileRepository prepo = context.getBean(ProfileRepository.class);
		AdminRepository arepo = context.getBean(AdminRepository.class);

	}

}
