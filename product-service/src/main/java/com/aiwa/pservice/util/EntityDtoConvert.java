package com.aiwa.pservice.util;

import com.aiwa.pservice.dto.ProductDto;
import com.aiwa.pservice.entity.Product;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

public class EntityDtoConvert {

    public static ProductDto toDto(final Product product) {
        //return new ProductDto(product.getId(), product.getName(), product.getPrice());
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    public static Product toProduct(final ProductDto dto) {
        // Using an utility class
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        //return new Product(dto.getId(), dto.getName(), dto.getPrice());
        return product;
    }
}
