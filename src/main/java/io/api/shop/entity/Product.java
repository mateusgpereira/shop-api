package io.api.shop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 110)
    private String name;
    @Column(nullable = false, precision = 10, scale = 2)
    private Double price;

}
