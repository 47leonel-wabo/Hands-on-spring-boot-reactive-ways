package com.aiwa.us.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class TransactionRequestDto {
    private final Long userId;
    private final BigDecimal amountValue;
}
