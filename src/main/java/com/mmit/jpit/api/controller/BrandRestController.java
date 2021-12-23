package com.mmit.jpit.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmit.jpit.model.entity.Brand;
import com.mmit.jpit.model.entity.Category;
import com.mmit.jpit.service.BrandService;

@RestController
@RequestMapping("api/brand/")
public class BrandRestController {
	@Autowired
	private BrandService service;
	@GetMapping("/all")
	public ResponseEntity<Message> allBrand(){
		try {
			List<Brand> list = service.findAll();
			return new ResponseEntity<Message>(new Message("success", list), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message("fail", null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/create")
	public ResponseEntity<Message> addnew(@RequestBody Brand brand){
		try {
			Brand existBrand = service.findByName(brand.getName());
			System.err.print("this is id"+existBrand);
			if(existBrand != null) {
				return new ResponseEntity<Message>(new Message("duplicate", null), HttpStatus.OK);
			}
			Brand brand1 =  service.save(brand);
			System.out.print(brand1.getId());
			System.err.print("this is id"+brand1.getId());
			Message msg = new Message("success", Arrays.asList(brand1));
			return new ResponseEntity<Message>(msg, HttpStatus.OK);
	} catch (Exception e) {
			Message msg = new Message("failure", null);
			e.printStackTrace();
			return new ResponseEntity<Message>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("find/{id}")
	public ResponseEntity<Message> getBrand(@PathVariable("id") int id){
		try {
			Optional<Brand> brand = service.findById(id);
			if(brand.isPresent()) {
				return new ResponseEntity<Message>(new Message("success", Arrays.asList(brand)), HttpStatus.OK);
			}
			return new ResponseEntity<Message>(new Message("fail", null), HttpStatus.NOT_FOUND);
			
	} catch (Exception e) {
			Message msg = new Message("failure", null);
			return new ResponseEntity<Message>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("edit/{id}")
	public ResponseEntity<Message> editBrand(@RequestBody Brand brand,@PathVariable("id") int id){
		try {
			Optional<Brand> opbrand = service.findById(id);
			if(opbrand.isEmpty()) {
				return new ResponseEntity<Message>(new Message("fail", null), HttpStatus.NOT_FOUND);
			}
			// check name already exist
			
			Brand existBrandName = service.findByName(brand.getId(),brand.getName());
			if(existBrandName != null) {
				return new ResponseEntity<Message>(new Message("duplicate", null), HttpStatus.OK);
			}
			Brand originalBrand = opbrand.get();
			originalBrand.setName(brand.getName());
			Brand savedBrand = service.save(originalBrand);
			Message msg = new Message("success", Arrays.asList(savedBrand));
			return new ResponseEntity<Message>(msg, HttpStatus.OK);
		} catch (Exception e) {
			Message msg = new Message("failure", null);
			return new ResponseEntity<Message>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Message> deleteBrand(@PathVariable("id") int id){
		try {
			Optional<Brand> brand = service.findById(id);
			if(brand.isPresent()) {
				service.delete(id);
				
			}
			Message msg = new Message("success", null);
			return new ResponseEntity<Message>(msg, HttpStatus.OK);
		} catch (Exception e) {
			Message msg = new Message("failure", null);
			return new ResponseEntity<Message>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
