package io.api.shop.service.impl;

import io.api.shop.dto.ProductDTO;
import io.api.shop.entity.Product;
import io.api.shop.exceptions.ProductNotFoundException;
import io.api.shop.mapper.ProductMapper;
import io.api.shop.repository.ProductRepository;
import io.api.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProductDTO> list() {
        return productRepository.findAll().stream()
                .map(product -> productMapper.productToProductDTO(product))
                .toList();
    }

    @Override
    public ProductDTO get(Long id) {
        return productMapper.productToProductDTO(
                productRepository.findById(id).orElseThrow(ProductNotFoundException::new)
        );
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product entity = productMapper.productDTOToProduct(productDTO);
        return productMapper.productToProductDTO(productRepository.save(entity));
    }
}
