package br.com.andreza.orderservice.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class OrderRequestDTO {

    @NotNull
    private Long userId;

    @NotEmpty
    private List<OrderItemRequestDTO> items;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<OrderItemRequestDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequestDTO> items) {
		this.items = items;
	}
    
}

