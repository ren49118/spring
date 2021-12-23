package com.mmit.jpit.web.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.mmit.jpit.model.entity.Product;
import com.mmit.jpit.service.ProductService;

@Controller
public class FrontendController {
	@Autowired
	private ProductService service;
	@GetMapping
	public String home(ModelMap m) {
		List<Product> list = service.findAll();
		m.put("products", list);
		return "/index";
	}
	@GetMapping("/product-detail")
	public String detail() {
		return "product-detail";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@GetMapping("/checkin")
	public String checkin() {
		return "checkin";
	}
	@GetMapping("/checkout")
	public String checkout(ModelMap m) {
		m.put("receiverName", "aung aung");
		m.put("receiverPhone", "094567821");
		m.put("receiverAddress", "Yangon");
		return "checkout";
	}
}
