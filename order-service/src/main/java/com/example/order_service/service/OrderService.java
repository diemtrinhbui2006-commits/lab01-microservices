package com.example.order_service.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.order_service.entity.Order;
import com.example.order_service.entity.OrderItem;
import com.example.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> getAllOrders() {
		return orderRepository.findByOrderByCreatedAtDesc();
	}

	public Optional<Order> getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	public Optional<Order> getOrderByCode(String orderCode) {
		return orderRepository.findByOrderCode(orderCode);
	}

	public List<Order> getOrdersByStatus(String status) {
		return orderRepository.findByStatus(status);
	}

	@Transactional
	public Order createOrder(Order order) {
		if (order.getOrderCode() == null || order.getOrderCode().isBlank()) {
			order.setOrderCode("ORD-" + System.currentTimeMillis() % 1000000);
		}
		if (order.getCreatedAt() == null) {
			order.setCreatedAt(LocalDateTime.now());
		}
		if (order.getStatus() == null || order.getStatus().isBlank()) {
			order.setStatus("PENDING");
		}

		BigDecimal total = BigDecimal.ZERO;
		if (order.getItems() != null && !order.getItems().isEmpty()) {
			for (OrderItem item : order.getItems()) {
				item.setOrder(order);
				if (item.getQuantity() == null || item.getQuantity() <= 0) {
					item.setQuantity(1);
				}
				if (item.getUnitPrice() == null) {
					item.setUnitPrice(BigDecimal.ZERO);
				}
				BigDecimal itemSubtotal = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
				item.setSubtotal(itemSubtotal);
				total = total.add(itemSubtotal);
			}
		}
		order.setTotalAmount(total);

		return orderRepository.save(order);
	}

	@Transactional
	public Optional<Order> updateOrderStatus(Long id, String status) {
		return orderRepository.findById(id).map(existing -> {
			existing.setStatus(status.toUpperCase());
			return orderRepository.save(existing);
		});
	}

	@Transactional
	public boolean deleteOrder(Long id) {
		if (orderRepository.existsById(id)) {
			orderRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
