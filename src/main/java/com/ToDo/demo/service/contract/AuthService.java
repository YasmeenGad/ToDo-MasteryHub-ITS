package com.ToDo.demo.service.contract;


import com.ToDo.demo.model.dto.request.ForgotPasswordRequestDto;
import com.ToDo.demo.model.dto.request.LoginRequestDto;
import com.ToDo.demo.model.dto.request.RegisterRequestDto;
import com.ToDo.demo.model.dto.request.ResetPasswordRequestDto;
import com.ToDo.demo.utils.base.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<BaseResponse> login(LoginRequestDto requestDto);
    ResponseEntity<BaseResponse> register(RegisterRequestDto request);
    ResponseEntity<BaseResponse> forgotPassword(ForgotPasswordRequestDto request);
    ResponseEntity<BaseResponse> resetPassword(ResetPasswordRequestDto request);
    ResponseEntity<BaseResponse> logout();
}
