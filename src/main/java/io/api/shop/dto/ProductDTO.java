package io.api.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotNull(message = "price is mandatory")
    @DecimalMin(value = "0.1", message = "value of price is invalid")
    private Double price;

    @Size(min = 13, max = 200)
    private String imageUrl;
}
