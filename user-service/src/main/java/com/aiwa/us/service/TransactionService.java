package com.aiwa.us.service;

import com.aiwa.us.dto.TransactionRequestDto;
import com.aiwa.us.dto.TransactionResponseDto;
import com.aiwa.us.entity.UserOperation;
import com.aiwa.us.repository.UserOperationRepository;
import com.aiwa.us.repository.UserRepository;
import com.aiwa.us.util.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.aiwa.us.dto.TransactionStatus.APPROUVED;
import static com.aiwa.us.dto.TransactionStatus.REJECTED;

@Service
public class TransactionService {

    private final UserOperationRepository mOperationRepository;
    private final UserRepository mUserRepository;

    @Autowired
    public TransactionService(UserOperationRepository operationRepository, UserRepository userRepository) {
        mOperationRepository = operationRepository;
        mUserRepository = userRepository;
    }

    public Mono<TransactionResponseDto> addTransaction(final TransactionRequestDto requestDto) {
        /*
         * 1) subtract targeted amount from user account
         * 2) if successful (meaning user have enough) then
         * 3) persist that operation in transaction table
         */
        return this.mUserRepository
                .updateUserBalance(requestDto.getUserId(), requestDto.getAmountValue())
                .filter(Boolean::booleanValue) // only if true, then continue
                .map(aBoolean -> EntityToDto.toUserOperation(requestDto))
                .flatMap(this.mOperationRepository::save)
                .map(userOperation -> EntityToDto.toTransactionResponse(requestDto, APPROUVED))
                .defaultIfEmpty(EntityToDto.toTransactionResponse(requestDto, REJECTED));
    }

    public Flux<UserOperation> fetchTransactionByUserId(final Long userId){
        return this.mOperationRepository.findByUserId(userId);
    }
}
