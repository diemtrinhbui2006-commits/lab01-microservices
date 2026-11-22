package com.example.order_service.repository;

import java.util.Optional;

import com.example.order_service.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	Optional<Cart> findBySessionId(String sessionId);
}
