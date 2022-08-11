package io.api.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, precision = 10, scale = 2)
    private Double price;
    @Column(nullable = true, length = 150)
    private String imageUrl;
}
