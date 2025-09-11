package com.ToDo.demo.service.impl;

import com.ToDo.demo.model.dto.request.ForgotPasswordRequestDto;
import com.ToDo.demo.model.dto.request.LoginRequestDto;
import com.ToDo.demo.model.dto.request.RegisterRequestDto;
import com.ToDo.demo.model.dto.request.ResetPasswordRequestDto;
import com.ToDo.demo.model.dto.response.LoginResponseDto;
import com.ToDo.demo.model.entity.Token;
import com.ToDo.demo.model.entity.UserEntity;
import com.ToDo.demo.model.mapper.LoginMapper;
import com.ToDo.demo.model.mapper.RegisterMapper;
import com.ToDo.demo.repository.TokenRepository;
import com.ToDo.demo.repository.UserRepository;
import com.ToDo.demo.security.JWTGenerator;
import com.ToDo.demo.service.contract.AuthService;
import com.ToDo.demo.utils.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private TokenRepository tokenRepository;


    @Override
    public ResponseEntity<BaseResponse> login(LoginRequestDto requestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword())
            );

            String token = jwtGenerator.generateToken(authentication);

            User principal = (User) authentication.getPrincipal();

            UserEntity user = userRepository.findByEmail(principal.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            saveToken(user, token);

            LoginResponseDto responseDto = LoginMapper.toDto(user, token);

            return ResponseEntity.status(HttpStatus.OK).body((new BaseResponse(true, "User logged in successfully", responseDto)));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new BaseResponse(false, "Invalid email or password", null));
        }
    }


    @Override
    public ResponseEntity<BaseResponse> register(RegisterRequestDto request) {

        boolean emailExists = userRepository.findByEmail(request.getEmail()).isPresent();

        if (emailExists) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse(false, "Email already exists", null));
        }

        boolean passwordsMatch = request.getPassword().equals(request.getConfirmPassword());

        if (!passwordsMatch) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse(false, "Passwords don't match", null));
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity user = RegisterMapper.toEntity(request, encodedPassword);

        UserEntity savedUser = userRepository.save(user);

        return ResponseEntity.ok(
                new BaseResponse(true, "User registered successfully", RegisterMapper.toDto(savedUser))
        );
    }

    @Override
    public ResponseEntity<BaseResponse> forgotPassword(ForgotPasswordRequestDto request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse(false, "Email not found", null));
        }

        return ResponseEntity.ok(
                new BaseResponse(true, "Email verified successfully. You can reset your password now.", null)
        );
    }

    @Override
    public ResponseEntity<BaseResponse> resetPassword(ResetPasswordRequestDto request) {
        boolean passwordsMatch = request.getNewPassword().equals(request.getConfirmPassword());

        if (!passwordsMatch) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse(false, "Passwords don't match", null));
        }

        UserEntity user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new BaseResponse(false, "User not found", null));
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(
                new BaseResponse(true, "Password reset successfully", null)
        );
    }

    @Override
    public ResponseEntity<BaseResponse> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(
                new BaseResponse(true, "Logged out successfully", null)
        );
    }

    private void saveToken(UserEntity user, String token) {
        Token tokenEntity = Token.builder()
                .token(token)
                .loggedOut(false)
                .user(user)
                .build();
        tokenRepository.save(tokenEntity);
    }
}
