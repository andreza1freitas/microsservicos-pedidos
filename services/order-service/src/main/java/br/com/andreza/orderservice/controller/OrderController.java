package br.com.andreza.orderservice.controller;

import br.com.andreza.orderservice.dto.*;
import br.com.andreza.orderservice.model.*;
import br.com.andreza.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public Order create(@RequestBody @Valid OrderRequestDTO dto) {

        Order order = new Order();
        order.setUserId(dto.getUserId());

        List<OrderItem> items = dto.getItems().stream()
            .map(i -> {
                OrderItem item = new OrderItem();
                item.setProductId(i.getProductId());
                item.setQuantity(i.getQuantity());
                item.setPrice(BigDecimal.valueOf(100)); // provisório
                return item;
            }).collect(Collectors.toList());

        return service.create(order, items);
    }

    @GetMapping
    public List<Order> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id) {
        return service.findById(id);
    }
}

