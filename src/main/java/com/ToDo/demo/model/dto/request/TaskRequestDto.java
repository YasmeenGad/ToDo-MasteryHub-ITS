package com.ToDo.demo.model.dto.request;

import com.ToDo.demo.utils.enums.TaskLabel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequestDto {

    @NotBlank(message = "Title is required")
    private String title;

    private String note;

    @NotNull(message = "Label is required")
    private TaskLabel label;

    @NotNull(message = "Date is required")
    private LocalDateTime date;
}
