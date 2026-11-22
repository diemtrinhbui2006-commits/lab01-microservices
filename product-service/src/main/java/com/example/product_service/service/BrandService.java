package com.example.product_service.service;

import java.util.List;
import java.util.Optional;

import com.example.product_service.entity.Brand;
import com.example.product_service.repository.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

	private final BrandRepository repository;

	public BrandService(BrandRepository repository) {
		this.repository = repository;
	}

	public List<Brand> getAll() {
		return repository.findAll();
	}

	public Optional<Brand> getById(Long id) {
		return repository.findById(id);
	}

	public Brand create(Brand brand) {
		return repository.save(brand);
	}

	public Optional<Brand> update(Long id, Brand updatedBrand) {
		return repository.findById(id).map(existing -> {
			existing.setName(updatedBrand.getName());
			existing.setDescription(updatedBrand.getDescription());
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
