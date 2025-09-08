package com.ToDo.demo.controller;


import com.ToDo.demo.model.dto.request.TaskRequestDto;
import com.ToDo.demo.service.contract.TaskService;
import com.ToDo.demo.utils.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addTask(@RequestBody @Validated TaskRequestDto request) {
        return taskService.addTask(request);
    }
}
