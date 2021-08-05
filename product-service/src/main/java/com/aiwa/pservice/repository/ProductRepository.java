package com.aiwa.pservice.repository;

import com.aiwa.pservice.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    @Query("select p from Product p where p.price >= :startPrice and p.price <= :endPrice")
    Flux<Product> findByPriceRange(final BigDecimal startPrice, final BigDecimal endPrice);

    // strictly greater than 'min' and strictly less than 'max'
    //Flux<Product> findByPriceBetween(final BigDecimal startPrice, final BigDecimal endPrice);

    // Using range
    Flux<Product> findByPriceBetween(Range<BigDecimal> price);
}
