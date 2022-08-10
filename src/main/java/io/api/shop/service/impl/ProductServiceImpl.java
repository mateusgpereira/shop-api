package io.api.shop.service.impl;

import io.api.shop.dto.ProductDTO;
import io.api.shop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductDTO> list() {
        return null;
    }

    @Override
    public ProductDTO get(Long id) {
        return null;
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        return null;
    }
}
