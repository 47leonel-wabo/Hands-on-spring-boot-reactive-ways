package com.aiwa.pservice.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ProductDto {
    private String id;
    private String name;
    private BigDecimal price;
}
