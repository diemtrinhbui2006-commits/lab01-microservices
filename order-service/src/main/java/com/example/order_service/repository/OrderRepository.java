package com.example.order_service.repository;

import java.util.List;
import java.util.Optional;

import com.example.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Optional<Order> findByOrderCode(String orderCode);
	List<Order> findByStatus(String status);
	List<Order> findByOrderByCreatedAtDesc();
}
