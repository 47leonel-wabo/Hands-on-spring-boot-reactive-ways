package com.aiwa.pservice.service;

import com.aiwa.pservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class InitializeDataService implements CommandLineRunner {

    private final ProductService mProductService;

    @Autowired
    public InitializeDataService(ProductService productService) {
        mProductService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        Flux.just(
                new ProductDto("Macbook 16\" pro M2", BigDecimal.valueOf(1200.2)),
                new ProductDto("32 inches monitor", BigDecimal.valueOf(850.2)),
                new ProductDto("Samsung Galaxy 21 pro", BigDecimal.valueOf(1400.0)),
                new ProductDto("Gamer chair Max 2", BigDecimal.valueOf(450.9))
        ).flatMap(dto -> this.mProductService.addProduct(Mono.just(dto)))
                .subscribe(System.out::println);
    }
}
