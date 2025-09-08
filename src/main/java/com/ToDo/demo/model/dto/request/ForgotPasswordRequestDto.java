package com.ToDo.demo.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgotPasswordRequestDto {

    @Email(message = "Please Enter a valid Email")
    @NotBlank(message = "Please Enter the Email")
    private String email;
}
