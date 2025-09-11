package com.ToDo.demo.model.dto.request;


import com.ToDo.demo.utils.constants.AppConstants;
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

    @NotBlank(message = AppConstants.passwordIsRequired)
    private String newPassword;

    @NotBlank(message = AppConstants.confirmPassword)
    private String confirmPassword;

    @NotBlank(message = AppConstants.enterEmail)
    private String email;
}
