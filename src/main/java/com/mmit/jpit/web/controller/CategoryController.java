package com.mmit.jpit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/categories")
public class CategoryController {
	@GetMapping
	public String home(ModelMap m) {
		m.put("page", "category");
		return "/admin/categories";
	}
}
