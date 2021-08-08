package com.aiwa.us.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private BigDecimal balance;

    public UserDto(String name, BigDecimal amount) {
        this.name = name;
        this.balance = amount;
    }
}
