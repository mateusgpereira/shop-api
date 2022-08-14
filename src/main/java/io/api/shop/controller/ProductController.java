package io.api.shop.controller;

import io.api.shop.dto.ProductDTO;
import io.api.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "25") Integer limit
    ) {
        if (name != null) {
            return  ResponseEntity.ok(productService.searchByName(name, page, limit));
        }

        return ResponseEntity.ok(productService.list(page,limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> get(@PathVariable Long id) {
        ProductDTO product = productService.get(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDTO));
    }
}
