package com.aft.monoproject.Spring.mono.project.controller;

import com.aft.monoproject.Spring.mono.project.dto.ApiResponse;
import com.aft.monoproject.Spring.mono.project.dto.user.request.CreateUserRequestDto;
import com.aft.monoproject.Spring.mono.project.dto.user.request.UpdateUserRequestDto;
import com.aft.monoproject.Spring.mono.project.dto.user.response.UserResponseDto;
import com.aft.monoproject.Spring.mono.project.entity.User;
import com.aft.monoproject.Spring.mono.project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Update user information", description = "Updates the details of an existing user.")
    @PutMapping("/update/{userId}")
    public ApiResponse<UserResponseDto> updateUser(@PathVariable Integer userId, @Valid @RequestBody UpdateUserRequestDto requestDto) {
        User updatedUser = userService.updateUser(userId, requestDto);
        return new ApiResponse<>(UserResponseDto.fromUser(updatedUser));
    }

    @Operation(summary = "Get user details", description = "Retrieves the details of a user by their ID.")
    @GetMapping("/{userId}")
    public ApiResponse<UserResponseDto> getUserById(@PathVariable Integer userId, Authentication authentication) {
        User user = userService.getAuthenticatedUserById(userId, authentication.getName());
        return new ApiResponse<>(UserResponseDto.fromUser(user));
    }
}