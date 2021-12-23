package com.mmit.jpit.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmit.jpit.model.entity.Product;
import com.mmit.jpit.service.ProductService;
@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	@Autowired
	private ProductService service;
	@GetMapping("/all")
	public ResponseEntity<Message> allproduct(){
		try {
			List<Product> list = service.findAll();
			return new ResponseEntity<Message>(new Message("success", list), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message("fail", null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("find/{id}")
	public ResponseEntity<Message> getProduct(@PathVariable("id") int id){
		try {
			Optional<Product> product = service.findProductById(id);
			if(product.isPresent()) {
				return new ResponseEntity<Message>(new Message("success", Arrays.asList(product)), HttpStatus.OK);
			}
			return new ResponseEntity<Message>(new Message("fail", null), HttpStatus.NOT_FOUND);
			
	} catch (Exception e) {
			Message msg = new Message("failure", null);
			return new ResponseEntity<Message>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
