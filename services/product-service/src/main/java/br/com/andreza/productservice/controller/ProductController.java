package br.com.andreza.productservice.controller;

import br.com.andreza.productservice.dto.*;
import br.com.andreza.productservice.mapper.ProductMapper;
import br.com.andreza.productservice.model.Product;
import br.com.andreza.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponseDTO create(@RequestBody @Valid ProductRequestDTO dto) {

		Product product = service.create(dto);

		return ProductMapper.toDTO(product);
	}

	@GetMapping
	public List<ProductResponseDTO> findAll() {

		return service.findAll().stream().map(ProductMapper::toDTO).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ProductResponseDTO findById(@PathVariable Long id) {

		return ProductMapper.toDTO(service.findById(id));
	}

	@PutMapping("/{id}")
	public ProductResponseDTO update(@PathVariable Long id, @RequestBody @Valid ProductRequestDTO dto) {

		Product product = ProductMapper.toEntity(dto);

		return ProductMapper.toDTO(service.update(id, product));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
