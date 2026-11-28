package com.example.product_service;

import java.math.BigDecimal;

import com.example.product_service.entity.Brand;
import com.example.product_service.entity.Category;
import com.example.product_service.entity.Product;
import com.example.product_service.repository.BrandRepository;
import com.example.product_service.repository.CategoryRepository;
import com.example.product_service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initDatabase(CategoryRepository categoryRepo, BrandRepository brandRepo, ProductRepository productRepo) {
		return args -> {
			try {
				if (categoryRepo.count() == 0) {
					Category c1 = categoryRepo.save(new Category(null, "Cà phê", "Cà phê pha phin truyền thống và pha máy"));
					Category c2 = categoryRepo.save(new Category(null, "Trà sữa", "Các món trà sữa đậm vị trà kèm topping"));
					Category c3 = categoryRepo.save(new Category(null, "Trà trái cây", "Trà hoa quả nhiệt đới tươi mát giải nhiệt"));
					Category c4 = categoryRepo.save(new Category(null, "Topping", "Trân châu, thạch, pudding"));

					Brand b1 = brandRepo.save(new Brand(null, "Arabica Cầu Đất", "Hạt cà phê nguyên chất Lâm Đồng"));
					Brand b2 = brandRepo.save(new Brand(null, "Trà Oolong Bảo Lộc", "Lá trà Oolong thượng hạng"));

					productRepo.save(new Product(null, "Cà phê muối", "Cà phê nguyên chất kết hợp lớp kem muối béo ngậy", new BigDecimal("35000"), true, c1, b1));
					productRepo.save(new Product(null, "Trà sữa Oolong nướng", "Trà sữa đậm vị thơm ngậy sốt nướng", new BigDecimal("45000"), true, c2, b2));
					productRepo.save(new Product(null, "Trà đào cam sả", "Trà đào thanh ngọt kết hợp sả và cam vàng", new BigDecimal("40000"), true, c3, b2));
					productRepo.save(new Product(null, "Trân châu đen đường đen", "Topping trân châu dẻo dai", new BigDecimal("10000"), true, c4, b2));
				}
			} catch (Exception e) {
				System.err.println("Data initialization skipped: " + e.getMessage());
			}
		};
	}
}
