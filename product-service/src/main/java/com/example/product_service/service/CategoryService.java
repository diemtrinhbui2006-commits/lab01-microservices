package com.example.product_service.service;

import java.util.List;
import java.util.Optional;

import com.example.product_service.entity.Category;
import com.example.product_service.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private final CategoryRepository repository;

	public CategoryService(CategoryRepository repository) {
		this.repository = repository;
	}

	public List<Category> getAll() {
		return repository.findAll();
	}

	public Optional<Category> getById(Long id) {
		return repository.findById(id);
	}

	public Category create(Category category) {
		return repository.save(category);
	}

	public Optional<Category> update(Long id, Category updatedCategory) {
		return repository.findById(id).map(existing -> {
			existing.setName(updatedCategory.getName());
			existing.setDescription(updatedCategory.getDescription());
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
}
