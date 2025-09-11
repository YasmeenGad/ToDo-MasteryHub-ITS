package com.ToDo.demo.model.dto.request;

import com.ToDo.demo.utils.constants.AppConstants;
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
public class LoginRequestDto {
    @Email(message = AppConstants.enterValidEmail)
    @NotBlank(message = AppConstants.enterEmail)
    private String email;

    @NotBlank(message = AppConstants.passwordIsRequired)
    private String password;

}
