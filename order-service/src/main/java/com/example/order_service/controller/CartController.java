package com.example.order_service.controller;

import com.example.order_service.entity.Cart;
import com.example.order_service.entity.CartItem;
import com.example.order_service.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/carts")
public class CartController {

	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping("/{sessionId}")
	public ResponseEntity<Cart> getCart(@PathVariable String sessionId) {
		return ResponseEntity.ok(cartService.getOrCreateCart(sessionId));
	}

	@PostMapping("/{sessionId}/items")
	public ResponseEntity<Cart> addItem(@PathVariable String sessionId, @RequestBody CartItem item) {
		Cart updated = cartService.addItem(sessionId, item);
		return ResponseEntity.status(HttpStatus.CREATED).body(updated);
	}

	@DeleteMapping("/items/{itemId}")
	public ResponseEntity<Void> removeItem(@PathVariable Long itemId) {
		if (cartService.removeItem(itemId)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{sessionId}")
	public ResponseEntity<Void> clearCart(@PathVariable String sessionId) {
		cartService.clearCart(sessionId);
		return ResponseEntity.noContent().build();
	}
}
