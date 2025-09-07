package com.ToDo.demo.service.contract;


import com.ToDo.demo.model.dto.request.LoginRequestDto;
import com.ToDo.demo.model.dto.request.RegisterRequestDto;
import com.ToDo.demo.model.dto.response.LoginResponseDto;
import com.ToDo.demo.model.dto.response.RegisterResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto requestDto);
    public ResponseEntity<RegisterResponseDto> register(RegisterRequestDto request);
}
