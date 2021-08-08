package com.aiwa.us.util;

import com.aiwa.us.dto.TransactionRequestDto;
import com.aiwa.us.dto.TransactionResponseDto;
import com.aiwa.us.dto.TransactionStatus;
import com.aiwa.us.dto.UserDto;
import com.aiwa.us.entity.User;
import com.aiwa.us.entity.UserOperation;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class EntityToDto {

    public static UserDto toUserDto(final User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static User toUserEntity(final UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    public static UserOperation toUserOperation(final TransactionRequestDto requestDto) {
        UserOperation operation = new UserOperation();
        operation.setOperationDate(LocalDateTime.now());
        operation.setAmount(requestDto.getAmountValue());
        operation.setUserId(requestDto.getUserId());
        return operation;
    }

    public static TransactionResponseDto toTransactionResponse(final TransactionRequestDto operation, final TransactionStatus status) {
        TransactionResponseDto responseDto = new TransactionResponseDto();
        responseDto.setStatus(status);
        responseDto.setAmountValue(operation.getAmountValue());
        responseDto.setUserId(operation.getUserId());
        return responseDto;
    }

}
