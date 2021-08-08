package com.aiwa.us.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
@Table("users")
public class User {

    @Id
    private Long id;
    private String name;
    private BigDecimal balance;

    public User(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }
}
