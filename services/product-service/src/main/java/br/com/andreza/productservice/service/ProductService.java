package br.com.andreza.productservice.service;

import br.com.andreza.productservice.model.Product;
import br.com.andreza.productservice.repository.ProductRepository;
import br.com.andreza.productservice.dto.ProductRequestDTO;
import br.com.andreza.productservice.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    public Product create(ProductRequestDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        return repository.save(product);
   }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Product update(Long id, Product product) {

        Product existing = findById(id);

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
