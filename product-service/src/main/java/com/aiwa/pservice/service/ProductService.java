package com.aiwa.pservice.service;

import com.aiwa.pservice.dto.ProductDto;
import com.aiwa.pservice.repository.ProductRepository;
import com.aiwa.pservice.util.EntityDtoConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository mProductRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        mProductRepository = productRepository;
    }

    public Flux<ProductDto> fetchProducts() {
        return this.mProductRepository
                .findAll()
                .map(EntityDtoConvert::toDto);
    }

    public Mono<ProductDto> fetchProductById(final String idStr) {
        return this.mProductRepository
                .findById(idStr)
                .map(EntityDtoConvert::toDto);
    }

    public Mono<ProductDto> addProduct(final Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .map(EntityDtoConvert::toProduct) // convert to 'product'
                .flatMap(this.mProductRepository::insert) // insert to db, now product has an ID
                .map(EntityDtoConvert::toDto); // convert back to dto (now has an id)
    }

    public Mono<ProductDto> updateProduct(final String id, final Mono<ProductDto> productDtoMono) {
        return this.mProductRepository
                .findById(id) // first get element by id
                .flatMap( // on the element retrieved
                        product -> productDtoMono
                                .map(EntityDtoConvert::toProduct) // convert passed new values to expected one
                                .doOnNext(p -> p.setId(id)) // add to this expected object an id
                )
                .flatMap(this.mProductRepository::save) // proceed saving
                .map(EntityDtoConvert::toDto); // convert object returned after saving to expected object to be shown
    }
}
