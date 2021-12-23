package com.mmit.jpit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmit.jpit.model.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
