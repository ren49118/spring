package com.mmit.jpit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mmit.jpit.model.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>   {
	
	Category findCategoryByName(String name);
	
	@Query("SELECT c FROM Category c WHERE c.name = :name AND c.id <> :id")
	Category findCatNameAndId(@Param("name")String name,@Param("id") int id);
}
