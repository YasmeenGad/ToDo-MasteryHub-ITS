package com.ToDo.demo.service.contract;

import com.ToDo.demo.model.dto.request.TaskRequestDto;
import com.ToDo.demo.model.dto.response.TaskResponseDto;
import com.ToDo.demo.utils.base.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface TaskService {
    ResponseEntity<BaseResponse> addTask(TaskRequestDto request, Long userId);

    ResponseEntity<BaseResponse> editTask(Long taskId, TaskRequestDto request, Long userId);
    ResponseEntity<BaseResponse> deleteTask(Long taskId, Long userId);
}
