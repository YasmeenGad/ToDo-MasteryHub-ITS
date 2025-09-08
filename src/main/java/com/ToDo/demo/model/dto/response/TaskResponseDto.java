package com.ToDo.demo.model.dto.response;

import com.ToDo.demo.utils.enums.TaskLabel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponseDto {
    private Long id;
    private String title;
    private String note;
    private TaskLabel label;
    private LocalDateTime date;
}
