package com.ToDo.demo.controller;

import com.ToDo.demo.model.dto.request.LoginRequestDto;
import com.ToDo.demo.model.dto.request.RegisterRequestDto;
import com.ToDo.demo.model.dto.response.LoginResponseDto;
import com.ToDo.demo.model.dto.response.RegisterResponseDto;
import com.ToDo.demo.service.contract.AuthService;
import com.ToDo.demo.utils.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService _authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody @Validated LoginRequestDto requestDto) {
        return _authService.login(requestDto);
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody @Validated RegisterRequestDto request) {
        return _authService.register(request);
    }
}
