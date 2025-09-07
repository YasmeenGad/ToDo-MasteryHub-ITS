package com.ToDo.demo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.*;

@Data
@Builder
public class LoginResponseDto {
    private String accessToken;
    private String message;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String accessToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
    }
}
