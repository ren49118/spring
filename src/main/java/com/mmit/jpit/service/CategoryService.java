package com.mmit.jpit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.jpit.model.entity.Category;
import com.mmit.jpit.repo.CategoryRepo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepo repo;

	public Category save(Category cat) {
		return repo.save(cat);
	}

	public List<Category> findAll() {
		
		return repo.findAll();
	}

	public Category findByName(String name) {
		
		return repo.findCategoryByName(name);
	}

	public Optional<Category> findById(int id) {
		
		return Optional.ofNullable(repo.findById(id).get());
	}

	public Category findByName(int id, String name) {
		
		return repo.findCatNameAndId(name, id);
	}

	public void delete(int id) {
		repo.deleteById(id);
		
	}
}
