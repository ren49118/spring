package com.mmit.jpit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mmit.jpit.model.entity.Users;
import com.mmit.jpit.model.entity.Users.Role;
import com.mmit.jpit.service.UserService;

@Configuration
public class AdminUserCreation {
	@Autowired
	private UserService service;
	@Bean
	public CommandLineRunner runner() {
		return args->{
			long count = service.countUser();
			if(count == 0) {
				Users user = new Users();
				user.setName("admin");
				user.setEmail("saikyawmyotun.it@gmail.com");
				user.setPassword("123");
				user.setPhone("09761051414");
				user.setRole(Role.Admin);
				service.save(user);
			}
		};
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
