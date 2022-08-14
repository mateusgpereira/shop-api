package io.api.shop.service;

import io.api.shop.dto.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<ProductDTO> list(Integer page, Integer limit);

    ProductDTO get(Long id);

    Page<ProductDTO> searchByName(String name, Integer page, Integer limit);

    ProductDTO create(ProductDTO productDTO);
}
