package io.api.shop.repository;

import io.api.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByNameContainsIgnoreCase(String nameToSearch, Pageable pageable);

}
