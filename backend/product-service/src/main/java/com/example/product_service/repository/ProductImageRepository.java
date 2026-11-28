package com.example.product_service.repository;

import java.util.List;
import com.example.product_service.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	List<ProductImage> findByProductId(Long productId);
}
