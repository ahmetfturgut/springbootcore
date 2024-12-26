package com.aft.monoproject.Spring.mono.project.controller;

import com.aft.monoproject.Spring.mono.project.dto.ApiResponse;
import com.aft.monoproject.Spring.mono.project.dto.user.request.CreateUserRequestDto;
import com.aft.monoproject.Spring.mono.project.dto.user.request.SignInRequestDto;
import com.aft.monoproject.Spring.mono.project.dto.user.request.VerifySignUpRequestDto;
import com.aft.monoproject.Spring.mono.project.dto.user.response.CreateUserResponseDto;
import com.aft.monoproject.Spring.mono.project.dto.user.response.VerifySignInResponseDto;
import com.aft.monoproject.Spring.mono.project.entity.Auth;
import com.aft.monoproject.Spring.mono.project.entity.User;
import com.aft.monoproject.Spring.mono.project.service.UserService;
import com.aft.monoproject.Spring.mono.project.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;


    @Operation(summary = "Create a new user", description = "Creates a new user in the system")
    @PostMapping("/signup")
    public ApiResponse<CreateUserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto requestDto) {
        User createdUser = userService.createUser(requestDto);
        Auth auth = authService.signup(createdUser);
        return new ApiResponse<>(CreateUserResponseDto.builder().token(auth.getToken()).build());
    }

    @Operation(summary = "Verify user sign-up", description = "Validates the user's sign-up using the provided token and verification code.")
    @PostMapping("/verifysignup")
    public ApiResponse<String> verifySignUp(@Valid @RequestBody VerifySignUpRequestDto requestDto) {
        authService.verifySignUp(requestDto.getToken(), requestDto.getCode());
        return new ApiResponse<>("User sign-up successfully verified.");
    }

    @PostMapping("/signin")
    public ApiResponse<VerifySignInResponseDto> signin(@Valid @RequestBody SignInRequestDto requestDto) {
        User user = requestDto.toUser();
        VerifySignInResponseDto response = authService.signin(user);
        return new ApiResponse<>(response);
    }

}
