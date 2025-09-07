package com.ToDo.demo.model.mapper;

import com.ToDo.demo.model.dto.request.RegisterRequestDto;
import com.ToDo.demo.model.dto.response.RegisterResponseDto;
import com.ToDo.demo.model.entity.UserEntity;

public class AuthMapper {

    public static UserEntity toUserEntity(RegisterRequestDto request, String encodedPassword) {
        return UserEntity.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .build();
    }

    public static RegisterResponseDto toRegisterResponseDto(UserEntity user) {
        return RegisterResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
