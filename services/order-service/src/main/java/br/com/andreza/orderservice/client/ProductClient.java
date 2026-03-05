package br.com.andreza.orderservice.client;

import br.com.andreza.orderservice.dto.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "http://localhost:8082")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponseDTO getProductById(@PathVariable Long id);

}
