package com.example.order_service.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.order_service.entity.Cart;
import com.example.order_service.entity.CartItem;
import com.example.order_service.repository.CartItemRepository;
import com.example.order_service.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;

	public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
	}

	public Cart getOrCreateCart(String sessionId) {
		return cartRepository.findBySessionId(sessionId).orElseGet(() -> {
			Cart newCart = new Cart(null, sessionId, null);
			return cartRepository.save(newCart);
		});
	}

	@Transactional
	public Cart addItem(String sessionId, CartItem item) {
		Cart cart = getOrCreateCart(sessionId);
		cart.addItem(item);
		cart.setUpdatedAt(LocalDateTime.now());
		return cartRepository.save(cart);
	}

	@Transactional
	public boolean removeItem(Long itemId) {
		if (cartItemRepository.existsById(itemId)) {
			cartItemRepository.deleteById(itemId);
			return true;
		}
		return false;
	}

	@Transactional
	public void clearCart(String sessionId) {
		cartRepository.findBySessionId(sessionId).ifPresent(cart -> {
			cart.getCartItems().clear();
			cartRepository.save(cart);
		});
	}
}
