package com.example.product_service;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@GetMapping
	public List<Map<String, Object>> getProducts() {
		return List.of(
			Map.of("id", 1, "name", "Mouse", "price", 150000),
			Map.of("id", 2, "name", "Keyboard", "price", 250000)
		);
	}

	@GetMapping("/{id}")
	public Map<String, Object> getProduct(@PathVariable int id) {
		return Map.of(
			"id", id,
			"name", "Mouse",
			"price", 150000
		);
	}
}

