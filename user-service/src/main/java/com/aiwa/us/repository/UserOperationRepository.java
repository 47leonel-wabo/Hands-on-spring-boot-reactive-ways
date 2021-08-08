package com.aiwa.us.repository;

import com.aiwa.us.entity.UserOperation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserOperationRepository extends ReactiveCrudRepository<UserOperation, Long> {

    Flux<UserOperation> findByUserId(final Long userId);

}
