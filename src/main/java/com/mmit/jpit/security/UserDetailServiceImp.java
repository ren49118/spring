package com.mmit.jpit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mmit.jpit.model.entity.Users;
import com.mmit.jpit.repo.UserRepo;


public class UserDetailServiceImp implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = repo.findUserByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Could not Find User");
		}
		return new MyUserDetail(user);
	}

}
