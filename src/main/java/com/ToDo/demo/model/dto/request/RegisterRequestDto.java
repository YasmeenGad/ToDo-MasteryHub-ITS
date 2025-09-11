package com.ToDo.demo.model.dto.request;

import com.ToDo.demo.utils.constants.AppConstants;
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

    @NotBlank(message = AppConstants.enterFirstName)
    private String firstName;

    @NotBlank(message = AppConstants.enterSecondName)
    private String secondName;

    @Email(message = AppConstants.enterValidEmail)
    @NotBlank(message = AppConstants.enterEmail)
    private String email;

    @NotBlank(message = AppConstants.passwordIsRequired)
    private String password;

    @NotBlank(message = AppConstants.confirmPassword)
    private String confirmPassword;
}
