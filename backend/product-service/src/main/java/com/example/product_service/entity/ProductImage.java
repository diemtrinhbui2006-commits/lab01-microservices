package com.example.product_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "product_images")
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Đường dẫn hình ảnh không được để trống")
	@Column(name = "image_url", nullable = false, length = 255)
	private String imageUrl;

	@Column(name = "is_primary", nullable = false)
	private Boolean isPrimary = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@JsonIgnore
	private Product product;

	public ProductImage() {
	}

	public ProductImage(Long id, String imageUrl, Boolean isPrimary, Product product) {
		this.id = id;
		this.imageUrl = imageUrl;
		this.isPrimary = isPrimary;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
