package com.aiwa.us.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
public class TransactionResponseDto {
    private Long userId;
    private BigDecimal amountValue;
    private TransactionStatus status;
}
