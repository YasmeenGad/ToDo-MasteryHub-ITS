package com.ToDo.demo.service.impl;

import com.ToDo.demo.model.dto.request.TaskRequestDto;
import com.ToDo.demo.model.dto.response.TaskResponseDto;
import com.ToDo.demo.model.entity.TaskEntity;
import com.ToDo.demo.model.entity.UserEntity;
import com.ToDo.demo.model.mapper.TaskMapper;
import com.ToDo.demo.repository.TaskRepository;
import com.ToDo.demo.repository.UserRepository;
import com.ToDo.demo.service.contract.TaskService;
import com.ToDo.demo.utils.base.BaseResponse;
import com.ToDo.demo.utils.enums.TaskLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<BaseResponse> addTask(TaskRequestDto request, Long userId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse(false, "User not found", null));
        }

        TaskEntity task = TaskMapper.toTaskEntity(request);
        task.setUser(userOpt.get());

        TaskEntity savedTask = taskRepository.save(task);

        return ResponseEntity.ok(
                new BaseResponse(true, "Task added successfully", TaskMapper.toTaskResponseDto(savedTask))
        );
    }

    @Override
    public ResponseEntity<BaseResponse> editTask(Long taskId, TaskRequestDto request, Long userId) {
        Optional<TaskEntity> taskOpt = taskRepository.findById(taskId);

        if (taskOpt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse(false, "Task not found", null));
        }

        TaskEntity task = taskOpt.get();

        if (!task.getUser().getId().equals(userId)) {
            return ResponseEntity
                    .status(403)
                    .body(new BaseResponse(false, "You are not authorized to edit this task", null));
        }

        task.setTitle(request.getTitle());
        task.setNote(request.getNote());
        task.setLabel(request.getLabel());
        task.setDate(request.getDate());

        TaskEntity updatedTask = taskRepository.save(task);

        return ResponseEntity.ok(
                new BaseResponse(true, "Task updated successfully", TaskMapper.toTaskResponseDto(updatedTask))
        );
    }

    @Override
    public ResponseEntity<BaseResponse> deleteTask(Long taskId, Long userId) {
        Optional<TaskEntity> taskOpt = taskRepository.findById(taskId);

        if (taskOpt.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse(false, "Task not found", null));
        }

        TaskEntity task = taskOpt.get();

        if (!task.getUser().getId().equals(userId)) {
            return ResponseEntity
                    .status(403)
                    .body(new BaseResponse(false, "You are not authorized to delete this task", null));
        }

        taskRepository.delete(task);

        return ResponseEntity.ok(
                new BaseResponse(true, "Task deleted successfully", null)
        );
    }

    @Override
    public ResponseEntity<BaseResponse> getAllTasks(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse(false, "User not found", null));
        }

        UserEntity user = userOptional.get();

        List<TaskResponseDto> tasks = taskRepository.findByUser(user)
                .stream()
                .map(TaskMapper::toTaskResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new BaseResponse(true, "Tasks fetched successfully", tasks)
        );

    }

    @Override
    public ResponseEntity<BaseResponse> getTasksByLabel(Long userId, TaskLabel label) {
        Optional<UserEntity> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse(false, "User not found", null));
        }

        UserEntity userEntity = user.get();

        List<TaskResponseDto> tasks = taskRepository.findByUserAndLabel(userEntity, label)
                .stream()
                .map(TaskMapper::toTaskResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new BaseResponse(true, "Tasks fetched successfully", tasks)
        );
    }
}
