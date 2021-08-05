package com.aiwa.pservice.controller;

import com.aiwa.pservice.dto.ProductDto;
import com.aiwa.pservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/product"})
public class ProductController {

    private final ProductService mProductService;

    @Autowired
    public ProductController(ProductService productService) {
        mProductService = productService;
    }

    @GetMapping(path = {"/all"})
    public Flux<ProductDto> allProducts() {
        return this.mProductService.fetchProducts();
    }

    @GetMapping(path = {"/{id}"})
    public Mono<ResponseEntity<ProductDto>> getProductById(final @PathVariable("id") String id) {
        return this.mProductService
                .fetchProductById(id)
                .map(ResponseEntity::ok) // if it exists OK 200
                .defaultIfEmpty(ResponseEntity.notFound().build()); // otherwise 404 NOT FOUND
    }

    @PostMapping
    public Mono<ResponseEntity<ProductDto>> addProduct(final @RequestBody Mono<ProductDto> productDtoMono) {
        return this.mProductService
                .addProduct(productDtoMono)
                .map(ResponseEntity::ok);
    }

    @PutMapping(path = {"/{id}"})
    public Mono<ResponseEntity<ProductDto>> updateProduct(
            final @PathVariable("id") String id,
            final @RequestBody Mono<ProductDto> productDtoMono
    ) {
        return this.mProductService
                .updateProduct(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public Mono<ResponseEntity<Void>> deleteProduct(final @PathVariable("id") String id) {
        return this.mProductService
                .deleteProduct(id)
                .map(ResponseEntity::ok);
    }
}
