package com.example.order_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_code", nullable = false, unique = true, length = 50)
	private String orderCode;

	@Column(name = "customer_name", length = 100)
	private String customerName;

	@Column(name = "customer_phone", length = 20)
	private String customerPhone;

	@Column(name = "table_number")
	private Integer tableNumber;

	@Column(name = "order_type", length = 20)
	private String orderType = "DINE_IN"; // DINE_IN (tai ban), TAKE_AWAY (mang ve)

	@NotNull(message = "Tổng tiền không được để trống")
	@Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
	private BigDecimal totalAmount = BigDecimal.ZERO;

	@Column(length = 30)
	private String status = "PENDING"; // PENDING, PREPARING, COMPLETED, CANCELLED

	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<OrderItem> items = new ArrayList<>();

	public Order() {
	}

	public Order(Long id, String orderCode, String customerName, String customerPhone, Integer tableNumber, String orderType, BigDecimal totalAmount, String status) {
		this.id = id;
		this.orderCode = orderCode;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.tableNumber = tableNumber;
		this.orderType = orderType;
		this.totalAmount = totalAmount;
		this.status = status;
		this.createdAt = LocalDateTime.now();
	}

	public void addItem(OrderItem item) {
		items.add(item);
		item.setOrder(this);
	}

	public void removeItem(OrderItem item) {
		items.remove(item);
		item.setOrder(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Integer getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(Integer tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
}
