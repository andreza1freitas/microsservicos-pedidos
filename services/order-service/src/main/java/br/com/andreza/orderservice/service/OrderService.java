package br.com.andreza.orderservice.service;

import br.com.andreza.orderservice.model.*;
import br.com.andreza.orderservice.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.andreza.orderservice.client.ProductClient;
import br.com.andreza.orderservice.client.UserClient;
import br.com.andreza.orderservice.dto.ProductResponseDTO;
import br.com.andreza.orderservice.dto.UserResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository itemRepository;
    private final ProductClient productClient;
    private final UserClient userClient;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository itemRepository,
                        ProductClient productClient,
                        UserClient userClient) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.productClient = productClient;
        this.userClient = userClient;
        
    }

    @Transactional
    public Order create(Order order, List<OrderItem> items) {

        // Validar usuário
        UserResponseDTO user = userClient.getUserById(order.getUserId());

        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {

            // Buscar produto
            ProductResponseDTO product =
                productClient.getProductById(item.getProductId());

            if (product == null) {
                throw new RuntimeException("Produto não encontrado");
            }

            // usar preço real do produto
            item.setPrice(product.price());

            item.setOrder(order);

            total = total.add(
                item.getPrice().multiply(
                    BigDecimal.valueOf(item.getQuantity())
                )
            );
        }

        order.setTotal(total);
        order.setItems(items);
        

        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}

