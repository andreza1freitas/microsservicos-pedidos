package br.com.andreza.orderservice.controller;

import br.com.andreza.orderservice.dto.*;
import br.com.andreza.orderservice.model.*;
import br.com.andreza.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponseDTO create(@RequestBody @Valid OrderRequestDTO dto) {

        Order order = new Order();
        order.setUserId(dto.getUserId());

        List<OrderItem> items = dto.getItems().stream()
            .map(i -> {
                OrderItem item = new OrderItem();
                item.setProductId(i.getProductId());
                item.setQuantity(i.getQuantity());
                return item;
            }).collect(Collectors.toList());
        
        Order savedOrder = service.create(order,  items);

        return convertToDTO(savedOrder);
    }

    @GetMapping
    public List<OrderResponseDTO> findAll() {
        return service.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderResponseDTO findById(@PathVariable Long id) {
        Order order = service.findById(id);

        return convertToDTO(order);
    }
    
    private OrderResponseDTO convertToDTO(Order order) {

        OrderResponseDTO dto = new OrderResponseDTO();

        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setTotal(order.getTotal());
        dto.setStatus(order.getStatus());
        dto.setCreatedAt(order.getCreatedAt());

        List<OrderItemRequestDTO> items = order.getItems()
                .stream()
                .map(item -> {
                    OrderItemRequestDTO i = new OrderItemRequestDTO();
                    i.setProductId(item.getProductId());
                    i.setQuantity(item.getQuantity());
                    return i;
                })
                .collect(Collectors.toList());

        dto.setItems(items);

        return dto;
    }
}

