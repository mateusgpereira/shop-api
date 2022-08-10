package io.api.shop.service;

import io.api.shop.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> list();

    ProductDTO get(Long id);

    ProductDTO create(ProductDTO productDTO);
}
