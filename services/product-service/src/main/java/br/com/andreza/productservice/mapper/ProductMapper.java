package br.com.andreza.productservice.mapper;

import br.com.andreza.productservice.dto.ProductRequestDTO;
import br.com.andreza.productservice.dto.ProductResponseDTO;
import br.com.andreza.productservice.model.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDTO dto) {

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        return product;
    }

    public static ProductResponseDTO toDTO(Product product) {

        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());

        return dto;
    }
}
