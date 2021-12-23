package com.mmit.jpit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmit.jpit.model.entity.Brand;

public interface BrandRepo extends JpaRepository<Brand, Integer> {
	Brand findBrandByName(String name);
	
	@Query("SELECT c FROM Brand c WHERE c.name = :name AND c.id <> :id")
	Brand findBrandNameAndId(@Param("name")String name,@Param("id") int id);
}
