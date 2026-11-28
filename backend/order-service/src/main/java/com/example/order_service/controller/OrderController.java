package com.example.order_service.controller;

import java.util.List;
import java.util.Map;

import com.example.order_service.entity.Order;
import com.example.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;
	private final RestClient restClient;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
		this.restClient = RestClient.builder()
			.baseUrl("http://localhost:8081")
			.build();
	}

	// 1. Lấy danh sách tất cả đơn hàng
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders() {
		return ResponseEntity.ok(orderService.getAllOrders());
	}

	// 2. Lấy chi tiết đơn hàng theo ID
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// 3. Tạo đơn hàng mới
	@PostMapping
	public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
		Order created = orderService.createOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	// 4. Cập nhật trạng thái đơn hàng (PENDING, PREPARING, COMPLETED, CANCELLED)
	@PutMapping("/{id}/status")
	public ResponseEntity<Order> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> statusBody) {
		String status = statusBody.get("status");
		if (status == null || status.isBlank()) {
			return ResponseEntity.badRequest().build();
		}
		return orderService.updateOrderStatus(id, status)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// 5. Xóa đơn hàng theo ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		if (orderService.deleteOrder(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	// 6. Giao tiếp gọi sang Product Service (Port 8081) qua RestClient (giữ nguyên từ Lab 01)
	@GetMapping("/product/{id}")
	public ResponseEntity<String> getProduct(@PathVariable int id) {
		try {
			String product = restClient.get()
				.uri("/api/products/{id}", id)
				.retrieve()
				.body(String.class);
			return ResponseEntity.ok(product);
		} catch (HttpClientErrorException.NotFound e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body("{\"error\": \"Không thể kết nối đến Product Service: " + e.getMessage() + "\"}");
		}
	}
}
