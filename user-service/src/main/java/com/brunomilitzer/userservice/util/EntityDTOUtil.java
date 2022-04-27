package com.brunomilitzer.userservice.util;

import com.brunomilitzer.userservice.dto.TransactionRequestDTO;
import com.brunomilitzer.userservice.dto.TransactionResponseDTO;
import com.brunomilitzer.userservice.dto.UserDTO;
import com.brunomilitzer.userservice.entity.User;
import com.brunomilitzer.userservice.entity.UserTransaction;
import com.brunomilitzer.userservice.enums.TransactionStatus;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class EntityDTOUtil {

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = UserDTO.builder().build();
        BeanUtils.copyProperties(user, userDTO);

        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = User.builder().build();
        BeanUtils.copyProperties(userDTO, user);

        return user;
    }

    public static UserTransaction toEntity(TransactionRequestDTO requestDTO) {
        return UserTransaction.builder().userId(requestDTO.getUserId()).amount(requestDTO.getAmount())
                .transactionDate(LocalDateTime.now()).build();
    }

    public static TransactionResponseDTO toDTO(TransactionRequestDTO requestDTO, TransactionStatus status) {
        return TransactionResponseDTO.builder().amount(requestDTO.getAmount())
                .userId(requestDTO.getUserId()).status(status).build();
    }

}
