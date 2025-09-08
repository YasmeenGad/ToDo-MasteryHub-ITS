package com.ToDo.demo.service.impl;

import com.ToDo.demo.model.dto.request.TaskRequestDto;
import com.ToDo.demo.model.entity.TaskEntity;
import com.ToDo.demo.model.mapper.TaskMapper;
import com.ToDo.demo.repository.TaskRepository;
import com.ToDo.demo.service.contract.TaskService;
import com.ToDo.demo.utils.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public ResponseEntity<BaseResponse> addTask(TaskRequestDto request) {
        TaskEntity task = TaskMapper.toTaskEntity(request);
        TaskEntity savedTask = taskRepository.save(task);

        return ResponseEntity.ok(
                new BaseResponse(true, "Task added successfully", TaskMapper.toTaskResponseDto(savedTask))
        );
    }
}
