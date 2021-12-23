package com.mmit.jpit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.mmit.jpit.model.entity.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
	
	@Query("SELECT u FROM Users u WHERE u.email = :email")
	Users findUserByEmail(@Param("email")String email);
	
	
}
