package com.mmit.jpit.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mmit.jpit.fileUpload;
import com.mmit.jpit.model.entity.Brand;
import com.mmit.jpit.model.entity.Category;
import com.mmit.jpit.model.entity.Product;
import com.mmit.jpit.service.BrandService;
import com.mmit.jpit.service.CategoryService;
import com.mmit.jpit.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private CategoryService catservice;
	@Autowired
	private BrandService brandservice;
	@Autowired
	private ProductService service;
	@GetMapping
	public String home(ModelMap m) {
		List<Product> list = service.findAll();
		m.put("products", list);
		m.put("page", "product");
		return "/admin/products";
	}
	@GetMapping("/add")
	public String add(ModelMap m) {
		List<Category> catlist = catservice.findAll();
		List<Brand> brandlist = brandservice.findAll();
		m.put("categories", catlist);
		m.put("brands", brandlist);
		m.put("product",new Product());
		return "/admin/add-product";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("product")Product product,
			@RequestParam("uploadPhoto")MultipartFile file,
			@RequestParam("oldFile")String oldFile) {
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		if(product.getId() != 0 && "".equals(fileName)) {
			product.setPhoto(oldFile);
		}else {
			product.setPhoto(fileName);
		}
		Product savedProduct = service.save(product);
		if(!"".equals(fileName)) {
			String uploadDir = "upload-photos/"+savedProduct.getId();
			fileUpload.saveFile(uploadDir, fileName, file);
		}
		return "redirect:/products";
	}
	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable("id")int proId,ModelMap m) {
		Product product= service.findById(proId);
		List<Category> catlist = catservice.findAll();
		List<Brand> brandlist = brandservice.findAll();
		m.put("categories", catlist);
		m.put("brands", brandlist);
		m.put("product", product);
		m.put("oldPhoto", product.getPhoto());
		m.put("page", "product");
		return "/admin/add-product";
	}
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id")int proId,ModelMap m) {
		service.delete(proId);
		return "redirect:/products";
	}
	
}
