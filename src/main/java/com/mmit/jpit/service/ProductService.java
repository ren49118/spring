package com.mmit.jpit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmit.jpit.model.entity.Product;
import com.mmit.jpit.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo repo;

	public Product save(Product pro) {
		return repo.save(pro);		
	}
	public List<Product> findAll() {
		return repo.findAll();
	}
	public Product findById(int proId) {
		
		return repo.findById(proId).get();
	}
	public void delete(int proId) {
		repo.deleteById(proId);
		
	}
	public Optional<Product> findProductById(int id) {
		
		return repo.findById(id);
	}
}
