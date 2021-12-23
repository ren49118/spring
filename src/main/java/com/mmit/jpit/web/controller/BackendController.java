package com.mmit.jpit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BackendController {
	@GetMapping("/dashboard")
	public String backend(ModelMap m ) {
		m.put("page", "dashboard");
		return "admin/dashboard";
	}
}
