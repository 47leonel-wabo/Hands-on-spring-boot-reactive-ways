package com.aiwa.us.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString
public class UserOperation {
    @Id
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime operationDate;
}
