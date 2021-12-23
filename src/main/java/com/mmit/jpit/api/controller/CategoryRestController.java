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

import com.mmit.jpit.model.entity.Category;
import com.mmit.jpit.service.CategoryService;


@RestController
@RequestMapping("/api/category")
public class CategoryRestController {
	@Autowired
	private CategoryService service;
	@GetMapping("/all")
	public ResponseEntity<Message> allcat(){
		try {
			List<Category> list = service.findAll();
			return new ResponseEntity<Message>(new Message("success", list), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Message>(new Message("fail", null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/create")
	public ResponseEntity<Message> addnew(@RequestBody Category cat){
		try {
			Category existCat = service.findByName(cat.getName());
			if(existCat != null) {
				return new ResponseEntity<Message>(new Message("duplicate", null), HttpStatus.OK);
			}
			Category category =  service.save(cat);
			Message msg = new Message("success", Arrays.asList(category));
			return new ResponseEntity<Message>(msg, HttpStatus.OK);
	} catch (Exception e) {
			Message msg = new Message("failure", null);
			e.printStackTrace();
			return new ResponseEntity<Message>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("find/{id}")
	public ResponseEntity<Message> getCatId(@PathVariable("id") int id){
		try {
			Optional<Category> cat = service.findById(id);
			if(cat.isPresent()) {
				return new ResponseEntity<Message>(new Message("success", Arrays.asList(cat)), HttpStatus.OK);
			}
			return new ResponseEntity<Message>(new Message("fail", null), HttpStatus.NOT_FOUND);
			
	} catch (Exception e) {
			Message msg = new Message("failure", null);
			return new ResponseEntity<Message>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("edit/{id}")
	public ResponseEntity<Message> editcat(@RequestBody Category cat,@PathVariable("id") int catid){
		try {
			Optional<Category> category = service.findById(catid);
			if(category.isEmpty()) {
				return new ResponseEntity<Message>(new Message("fail", null), HttpStatus.NOT_FOUND);
			}
			// check name already exist
			
			Category existCategoryName = service.findByName(category.get().getId(),cat.getName());
			if(existCategoryName != null) {
				return new ResponseEntity<Message>(new Message("duplicate", null), HttpStatus.OK);
			}
			Category originalCat = category.get();
			originalCat.setName(cat.getName());
			Category savedCat = service.save(originalCat);
			Message msg = new Message("success", Arrays.asList(savedCat));
			return new ResponseEntity<Message>(msg, HttpStatus.OK);
		} catch (Exception e) {
			Message msg = new Message("failure", null);
			return new ResponseEntity<Message>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Message> deletecat(@PathVariable("id") int id){
		try {
			Optional<Category> category = service.findById(id);
			if(category.isPresent()) {
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
