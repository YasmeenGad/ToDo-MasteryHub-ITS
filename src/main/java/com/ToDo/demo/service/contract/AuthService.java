package com.ToDo.demo.service.contract;


import com.ToDo.demo.model.dto.request.LoginRequestDto;
import com.ToDo.demo.model.dto.request.RegisterRequestDto;
import com.ToDo.demo.model.dto.response.LoginResponseDto;
import com.ToDo.demo.model.dto.response.RegisterResponseDto;
import com.ToDo.demo.utils.base.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<BaseResponse> login(LoginRequestDto requestDto);
    public ResponseEntity<BaseResponse> register(RegisterRequestDto request);
}
