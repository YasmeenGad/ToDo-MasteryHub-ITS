package com.ToDo.demo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String accessToken;
    private String userId;
    private String firstName;
    private String lastName;
}
