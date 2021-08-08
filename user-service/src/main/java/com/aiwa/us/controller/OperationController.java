package com.aiwa.us.controller;

import com.aiwa.us.dto.TransactionRequestDto;
import com.aiwa.us.dto.TransactionResponseDto;
import com.aiwa.us.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/transaction"})
public class OperationController {

    private final TransactionService mTransactionService;

    @Autowired
    public OperationController(TransactionService transactionService) {
        mTransactionService = transactionService;
    }

    @PostMapping
    public Mono<ResponseEntity<TransactionResponseDto>> makeTransaction(final @RequestBody TransactionRequestDto requestDto) {
        return this.mTransactionService
                .addTransaction(requestDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
