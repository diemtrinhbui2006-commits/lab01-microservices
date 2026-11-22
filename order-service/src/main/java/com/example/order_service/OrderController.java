package com.example.order_service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final RestClient restClient;

	public OrderController() {
		this.restClient = RestClient.builder()
			.baseUrl("http://localhost:8081")
			.build();
	}

	@GetMapping
	public List<Map<String, Object>> getOrders() {
		return List.of(
			Map.of("id", 101, "productId", 1, "quantity", 2),
			Map.of("id", 102, "productId", 2, "quantity", 1)
		);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<String> getProduct(@PathVariable int id) {
		try {
			String product = restClient.get()
				.uri("/api/products/{id}", id)
				.retrieve()
				.body(String.class);
			return ResponseEntity.ok(product);
		} catch (HttpClientErrorException.NotFound e) {
			return ResponseEntity.notFound().build();
		}
	}
}
