package com.ToDo.demo.model.mapper;


import com.ToDo.demo.model.dto.request.TaskRequestDto;
import com.ToDo.demo.model.dto.response.TaskResponseDto;
import com.ToDo.demo.model.entity.TaskEntity;

public class TaskMapper {

    public static TaskEntity toTaskEntity(TaskRequestDto dto) {
        return TaskEntity.builder()
                .title(dto.getTitle())
                .note(dto.getNote())
                .label(dto.getLabel())
                .date(dto.getDate())
                .build();
    }

    public static TaskResponseDto toTaskResponseDto(TaskEntity entity) {
        return TaskResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .note(entity.getNote())
                .label(entity.getLabel())
                .date(entity.getDate())
                .build();
    }
}
