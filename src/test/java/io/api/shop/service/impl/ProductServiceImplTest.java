package io.api.shop.service.impl;

import io.api.shop.dto.ProductDTO;
import io.api.shop.entity.Product;
import io.api.shop.exceptions.ProductNotFoundException;
import io.api.shop.mapper.ProductMapper;
import io.api.shop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @Spy
    @Autowired
    ProductMapper productMapper;

    @InjectMocks
    ProductServiceImpl productService;

    private String productBaseUrl = "http://image.io/";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldListTwoProducts() {
        Pageable pageable = PageRequest.of(0, 25);
        when(productRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(generateProductList(2)));

        Page<ProductDTO> result = productService.list(0, 25);

        assertNotNull(result);
        assertThat(result.getContent(), hasSize(2));
        assertEquals(1L, result.getContent().get(0).getId());
        assertEquals("product0", result.getContent().get(0).getName());
        assertEquals(0.5, result.getContent().get(0).getPrice());
        assertEquals(productBaseUrl + "product0", result.getContent().get(0).getImageUrl());

        verify(productRepository, times(1)).findAll(pageable);
        verify(productMapper, times(2)).productToProductDTO(any(Product.class));
    }

    @Test
    void shouldReturnEmptyWhenThereAreNoProducts() {
        Pageable pageable = PageRequest.of(0, 25);
        when(productRepository.findAll(pageable)).thenReturn(new PageImpl<>(new ArrayList<>()));

        Page<ProductDTO> result = productService.list(0, 25);

        assertNotNull(result);
        assertEquals(0, result.getTotalElements());

        verify(productRepository, times(1)).findAll(pageable);
        verify(productMapper, never()).productToProductDTO(any(Product.class));
    }

    @Test
    void shouldGetProductWithId200() {
        Product entity = this.createProductEntity(200L, "GreatProduct", 200.9);
        when(productRepository.findById(200L)).thenReturn(
                Optional.of(entity)
        );

        ProductDTO result = productService.get(200L);

        assertNotNull(result);
        assertEquals(200L, result.getId());
        assertEquals("GreatProduct", result.getName());
        assertEquals(200.9, result.getPrice());
        assertEquals(productBaseUrl + "GreatProduct", result.getImageUrl());

        verify(productRepository, times(1)).findById(200L);
        verify(productMapper, times(1)).productToProductDTO(entity);
    }

    @Test
    void shouldThrowsProductNotFoundException() {
        when(productRepository.findById(500L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,() -> {
            productService.get(500L);
        });

        verify(productRepository, times(1)).findById(500L);
    }

    @Test
    void shouldCreateProduct() {
        Product productToPersist = this.createProductEntity(null, "300", 300.9);
        Product entity = this.createProductEntity(300L, "300", 300.9);

        when(productRepository.save(productToPersist)).thenReturn(entity);

        ProductDTO productDTO = this.createProductDTO("300", 300.9);
        ProductDTO result = productService.create(productDTO);

        assertNotNull(result);
        assertEquals(300L, result.getId());
        assertEquals("300", result.getName());
        assertEquals(300.9, result.getPrice());
        assertEquals(productBaseUrl + "300", result.getImageUrl());

        verify(productRepository, times(1)).save(productToPersist);
        verify(productMapper, times(1)).productDTOToProduct(productDTO);
        verify(productMapper, times(1)).productToProductDTO(entity);
    }

    private Product createProductEntity(Long id, String name, Double price) {
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .imageUrl(productBaseUrl + name)
                .build();
    }

    private ProductDTO createProductDTO(String name, Double price) {
        return ProductDTO.builder()
                .name(name)
                .price(price)
                .imageUrl(productBaseUrl + name)
                .build();
    }

    private List<Product> generateProductList(Integer size) {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(createProductEntity(i + 1L, "product" + i, i + 0.5));
        }
        return list;
    }
}