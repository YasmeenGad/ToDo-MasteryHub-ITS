package com.ToDo.demo.model.dto.request;

import com.ToDo.demo.utils.constants.AppConstants;
import com.ToDo.demo.utils.enums.TaskLabel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequestDto {

    @NotBlank(message = AppConstants.titleIsRequired)
    private String title;

    private String note;

    @NotNull(message = AppConstants.labelIsRequired)
    private TaskLabel label;

    @NotNull(message = AppConstants.dateIsRequired)
    private LocalDate date;
}
