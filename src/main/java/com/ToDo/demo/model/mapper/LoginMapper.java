package com.ToDo.demo.model.mapper;

import com.ToDo.demo.model.dto.response.LoginResponseDto;
import com.ToDo.demo.model.entity.UserEntity;

public class LoginMapper {
    public static LoginResponseDto toDto(UserEntity user, String token) {
        return LoginResponseDto.builder()
                .accessToken(token)
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getSecondName())
                .build();
    }
}
