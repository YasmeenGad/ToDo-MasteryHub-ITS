package com.ToDo.demo.controller;

import com.ToDo.demo.model.dto.request.ForgotPasswordRequestDto;
import com.ToDo.demo.model.dto.request.LoginRequestDto;
import com.ToDo.demo.model.dto.request.RegisterRequestDto;
import com.ToDo.demo.model.dto.request.ResetPasswordRequestDto;
import com.ToDo.demo.service.contract.AuthService;
import com.ToDo.demo.utils.base.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody @Valid LoginRequestDto requestDto) {
        return authService.login(requestDto);
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody @Valid RegisterRequestDto request) {
        return authService.register(request);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<BaseResponse> forgotPassword(@RequestBody @Valid ForgotPasswordRequestDto requestDto) {
        return authService.forgotPassword(requestDto);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<BaseResponse> resetPassword(@RequestBody @Valid ResetPasswordRequestDto requestDto) {
        return authService.resetPassword(requestDto);
    }
}