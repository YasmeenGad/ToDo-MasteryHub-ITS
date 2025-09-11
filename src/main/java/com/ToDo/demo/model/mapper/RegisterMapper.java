package com.ToDo.demo.model.mapper;

import com.ToDo.demo.model.dto.request.RegisterRequestDto;
import com.ToDo.demo.model.dto.response.RegisterResponseDto;
import com.ToDo.demo.model.entity.UserEntity;

public class RegisterMapper {

    public static UserEntity toEntity(RegisterRequestDto request, String encodedPassword) {
        return UserEntity.builder()
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();
    }

    public static RegisterResponseDto toDto(UserEntity user) {
        return RegisterResponseDto.builder()
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
