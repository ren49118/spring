package com.mmit.jpit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mmit.jpit.model.entity.Users;
import com.mmit.jpit.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo repo;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void save(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repo.save(user);
	}

	public List<Users> findAll() {
		
		return repo.findAll();
	}

	public Users findById(int id) {
		
		return repo.findById(id).get();
	}

	public void delete(int userId) {
		repo.deleteById(userId);
	}

	public long countUser() {
		
		return repo.count();
	}
}
