package com.ToDo.demo.controller;


import com.ToDo.demo.model.dto.request.TaskRequestDto;
import com.ToDo.demo.service.contract.TaskService;
import com.ToDo.demo.utils.base.BaseResponse;
import com.ToDo.demo.utils.enums.TaskLabel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addTask(
            @RequestBody @Valid TaskRequestDto request,
            @RequestParam Long userId
    ) {
        return taskService.addTask(request, userId);
    }

    @PutMapping("/edit/{taskId}")
    public ResponseEntity<BaseResponse> editTask(
            @PathVariable Long taskId,
            @RequestBody @Valid TaskRequestDto request,
            @RequestParam Long userId
    ) {
        return taskService.editTask(taskId, request, userId);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<BaseResponse> deleteTask(
            @PathVariable Long taskId,
            @RequestParam Long userId
    ) {
        return taskService.deleteTask(taskId, userId);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<BaseResponse> getAllTasks(@PathVariable Long userId) {
        return taskService.getAllTasks(userId);
    }

    @GetMapping("/by-label")
    public ResponseEntity<BaseResponse> getTasksByLabel(@RequestParam Long userId, @RequestParam TaskLabel label) {
        return taskService.getTasksByLabel(userId, label);
    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<BaseResponse> markTaskCompleted(
            @PathVariable Long taskId,
            @RequestParam Long userId
    ) {
        return taskService.markTaskCompleted(taskId, userId);
    }

    @PutMapping("/complete-all")
    public ResponseEntity<BaseResponse> markAllTasksCompleted(
            @RequestParam Long userId
    ) {
        return taskService.markAllTasksCompleted(userId);
    }

    @DeleteMapping("/delete-completed")
    public ResponseEntity<BaseResponse> deleteAllCompletedTasks(
            @RequestParam Long userId
    ) {
        return taskService.deleteAllCompletedTasks(userId);
    }
}
