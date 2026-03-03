package br.com.andreza.orderservice.dto;

import jakarta.validation.constraints.*;

public class OrderItemRequestDTO {

    @NotNull
    private Long productId;

    @Min(1)
    private Integer quantity;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

    
}

