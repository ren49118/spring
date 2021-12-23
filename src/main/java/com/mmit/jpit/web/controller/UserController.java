package com.mmit.jpit.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mmit.jpit.model.entity.Users;
import com.mmit.jpit.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public String home(ModelMap m) {
		List<Users> list = service.findAll();
		m.put("page", "user");
		m.put("users",list);
		return "admin/users";
	}
	@GetMapping("/add")
	public String addnew(ModelMap m) {
		m.put("user", new Users());
		m.put("page", "user");
		return "/admin/add-user";
	}
	@PostMapping("/save")
	public String create(@ModelAttribute("users") Users user) {
		 service.save(user);
		 return "redirect:/users";
	}
	@GetMapping("/edit/{id}")
	public String edit(ModelMap m,@PathVariable("id")int id) {
		Users user = service.findById(id);
		m.put("page", "user");
		m.put("user", user);
		return "/admin/add-user";
	}
	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id")int userId) {
		service.delete(userId);
		return"redirect:/users";
	}
	
}
