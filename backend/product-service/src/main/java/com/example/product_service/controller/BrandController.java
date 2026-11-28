package com.example.product_service.controller;

import java.util.List;

import com.example.product_service.entity.Brand;
import com.example.product_service.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/brands")
public class BrandController {

	private final BrandService service;

	public BrandController(BrandService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Brand>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Brand> getById(@PathVariable Long id) {
		return service.getById(id)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Brand> create(@Valid @RequestBody Brand brand) {
		Brand created = service.create(brand);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Brand> update(@PathVariable Long id, @Valid @RequestBody Brand brand) {
		return service.update(id, brand)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
