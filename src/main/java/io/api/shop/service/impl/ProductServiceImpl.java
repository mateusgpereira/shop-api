package io.api.shop.service.impl;

import io.api.shop.dto.ProductDTO;
import io.api.shop.entity.Product;
import io.api.shop.exceptions.ProductNotFoundException;
import io.api.shop.mapper.ProductMapper;
import io.api.shop.repository.ProductRepository;
import io.api.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public Page<ProductDTO> list(Integer page, Integer limit) {
        return productRepository.findAll(PageRequest.of(page, limit)).map(productMapper::productToProductDTO);
    }

    @Override
    public ProductDTO get(Long id) {
        return productMapper.productToProductDTO(
                productRepository.findById(id).orElseThrow(ProductNotFoundException::new)
        );
    }

    @Override
    public Page<ProductDTO> searchByName(String name, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return productRepository.findAllByNameContainsIgnoreCase(name, pageable).map(productMapper::productToProductDTO);
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product entity = productMapper.productDTOToProduct(productDTO);
        return productMapper.productToProductDTO(productRepository.save(entity));
    }
}
