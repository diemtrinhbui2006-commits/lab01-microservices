package com.example.product_service.service;

import java.util.List;
import java.util.Optional;

import com.example.product_service.entity.Product;
import com.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public List<Product> getAll() {
		return repository.findAll();
	}

	public Optional<Product> getById(Long id) {
		return repository.findById(id);
	}

	public Product create(Product product) {
		return repository.save(product);
	}

	public Optional<Product> update(Long id, Product updatedProduct) {
		return repository.findById(id).map(existing -> {
			existing.setName(updatedProduct.getName());
			existing.setDescription(updatedProduct.getDescription());
			existing.setPrice(updatedProduct.getPrice());
			existing.setIsAvailable(updatedProduct.getIsAvailable());
			if (updatedProduct.getCategory() != null) {
				existing.setCategory(updatedProduct.getCategory());
			}
			if (updatedProduct.getBrand() != null) {
				existing.setBrand(updatedProduct.getBrand());
			}
			return repository.save(existing);
		});
	}

	public boolean delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

	public List<Product> searchByName(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}

	public List<Product> getByCategory(Long categoryId) {
		return repository.findByCategoryId(categoryId);
	}
}
