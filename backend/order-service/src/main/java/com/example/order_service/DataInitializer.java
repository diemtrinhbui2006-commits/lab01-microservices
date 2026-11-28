package com.example.order_service;

import java.math.BigDecimal;

import com.example.order_service.entity.Order;
import com.example.order_service.entity.OrderItem;
import com.example.order_service.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initOrderDatabase(OrderRepository orderRepository) {
		return args -> {
			try {
				if (orderRepository.count() == 0) {
					Order sampleOrder = new Order(
						null,
						"ORD-1001",
						"Nguyễn Văn An",
						"0901234567",
						5,
						"DINE_IN",
						BigDecimal.ZERO,
						"PREPARING"
					);

					OrderItem item1 = new OrderItem(
						null,
						2L,
						"Trà sữa Oolong nướng",
						new BigDecimal("45000"),
						2,
						"L",
						"50%",
						"70%",
						"Thêm trân châu đen",
						new BigDecimal("90000")
					);

					OrderItem item2 = new OrderItem(
						null,
						1L,
						"Cà phê muối",
						new BigDecimal("35000"),
						1,
						"M",
						"100%",
						"100%",
						"Ít đá",
						new BigDecimal("35000")
					);

					sampleOrder.addItem(item1);
					sampleOrder.addItem(item2);
					sampleOrder.setTotalAmount(new BigDecimal("125000"));

					orderRepository.save(sampleOrder);
				}
			} catch (Exception e) {
				System.err.println("Order data initialization skipped: " + e.getMessage());
			}
		};
	}
}
