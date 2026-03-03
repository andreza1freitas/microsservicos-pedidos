package br.com.andreza.orderservice.service;

import br.com.andreza.orderservice.model.*;
import br.com.andreza.orderservice.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public Order create(Order order, List<OrderItem> items) {

        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {
            item.setOrder(savedOrder);

            total = total.add(
                item.getPrice().multiply(
                    BigDecimal.valueOf(item.getQuantity())
                )
            );

            itemRepository.save(item);
        }

        savedOrder.setTotal(total);

        return orderRepository.save(savedOrder);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}

