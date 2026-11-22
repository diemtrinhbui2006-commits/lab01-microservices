package com.example.product_service.repository;

import java.util.List;
import com.example.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategoryId(Long categoryId);
	List<Product> findByNameContainingIgnoreCase(String name);
}
