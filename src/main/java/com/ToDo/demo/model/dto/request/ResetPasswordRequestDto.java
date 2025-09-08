package com.ToDo.demo.model.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordRequestDto {

    @NotBlank(message = "Please Enter the password")
    private String newPassword;

    @NotBlank(message = "please confirm the password")
    private String confirmPassword;

    @NotBlank(message = "Email is required")
    private String email;
}
