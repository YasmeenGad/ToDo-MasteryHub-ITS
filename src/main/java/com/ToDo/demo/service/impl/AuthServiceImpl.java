package com.ToDo.demo.service.impl;

import com.ToDo.demo.model.dto.request.LoginRequestDto;
import com.ToDo.demo.model.dto.request.RegisterRequestDto;
import com.ToDo.demo.model.dto.response.LoginResponseDto;
import com.ToDo.demo.model.dto.response.RegisterResponseDto;
import com.ToDo.demo.model.entity.UserEntity;
import com.ToDo.demo.model.mapper.AuthMapper;
import com.ToDo.demo.repository.UserRepository;
import com.ToDo.demo.security.JWTGenerator;
import com.ToDo.demo.service.contract.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword())
        );
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.ok(new LoginResponseDto(token, "User Logged Successfully"));
    }

    @Override
    public ResponseEntity<RegisterResponseDto> register(RegisterRequestDto request) {

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity user = AuthMapper.toUserEntity(request, encodedPassword);

        UserEntity savedUser = userRepository.save(user);

        return ResponseEntity.ok(AuthMapper.toRegisterResponseDto(savedUser));
    }
}
