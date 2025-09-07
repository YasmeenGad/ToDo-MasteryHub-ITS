package com.ToDo.demo.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @Email(message = "Please Enter a valid email")
    @NotBlank(message = "Please Enter Email")
    private String email;

    @NotBlank(message = "Please Enter Password")
    private String password;
}
