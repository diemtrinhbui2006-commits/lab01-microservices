package com.example.order_service.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "session_id", nullable = false, length = 100)
	private String sessionId;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt = LocalDateTime.now();

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<CartItem> cartItems = new ArrayList<>();

	public Cart() {
	}

	public Cart(Long id, String sessionId, Long customerId) {
		this.id = id;
		this.sessionId = sessionId;
		this.customerId = customerId;
		this.updatedAt = LocalDateTime.now();
	}

	public void addItem(CartItem item) {
		cartItems.add(item);
		item.setCart(this);
	}

	public void removeItem(CartItem item) {
		cartItems.remove(item);
		item.setCart(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
}
