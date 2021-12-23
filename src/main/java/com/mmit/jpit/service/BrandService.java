package com.mmit.jpit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.jpit.model.entity.Brand;
import com.mmit.jpit.repo.BrandRepo;

@Service
public class BrandService {
	@Autowired
	private BrandRepo repo;

	public List<Brand> findAll() {
		
		return repo.findAll();
	}

	public Optional<Brand> findById(int id) {
		
		return repo.findById(id);
	}

	public Brand findByName(String name) {
		
		return repo.findBrandByName(name);
	}

	public Brand save(Brand brand) {
		// TODO Auto-generated method stub
		return repo.save(brand);
	}

	public Brand findByName(int id, String name) {
		
		return repo.findBrandNameAndId(name, id);
	}

	public void delete(int id) {
		repo.deleteById(id);
		
	}
}
