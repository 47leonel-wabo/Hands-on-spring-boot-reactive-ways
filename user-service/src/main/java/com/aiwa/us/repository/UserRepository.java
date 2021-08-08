package com.aiwa.us.repository;

import com.aiwa.us.entity.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    @Modifying
    @Query("update users set balance = balance - :amount where id = :userId and balance >= :amount")
    Mono<Boolean> updateUserBalance(final Long userId, final BigDecimal amount);

}
