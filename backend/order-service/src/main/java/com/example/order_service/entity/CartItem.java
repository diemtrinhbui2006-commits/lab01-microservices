package com.example.order_service.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_id", nullable = false)
	private Long productId;

	@Column(name = "product_name", length = 150)
	private String productName;

	@Column(name = "unit_price", precision = 12, scale = 2)
	private BigDecimal unitPrice;

	private Integer quantity = 1;

	@Column(length = 10)
	private String size = "M";

	@Column(name = "sugar_level", length = 10)
	private String sugarLevel = "100%";

	@Column(name = "ice_level", length = 10)
	private String iceLevel = "100%";

	@Column(name = "topping_notes", length = 255)
	private String toppingNotes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	@JsonIgnore
	private Cart cart;

	public CartItem() {
	}

	public CartItem(Long id, Long productId, String productName, BigDecimal unitPrice, Integer quantity, String size, String sugarLevel, String iceLevel, String toppingNotes) {
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.size = size;
		this.sugarLevel = sugarLevel;
		this.iceLevel = iceLevel;
		this.toppingNotes = toppingNotes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSugarLevel() {
		return sugarLevel;
	}

	public void setSugarLevel(String sugarLevel) {
		this.sugarLevel = sugarLevel;
	}

	public String getIceLevel() {
		return iceLevel;
	}

	public void setIceLevel(String iceLevel) {
		this.iceLevel = iceLevel;
	}

	public String getToppingNotes() {
		return toppingNotes;
	}

	public void setToppingNotes(String toppingNotes) {
		this.toppingNotes = toppingNotes;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
