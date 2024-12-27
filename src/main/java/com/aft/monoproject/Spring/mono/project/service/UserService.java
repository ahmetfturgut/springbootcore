package com.aft.monoproject.Spring.mono.project.service;

import com.aft.monoproject.Spring.mono.project.dto.user.request.CreateUserRequestDto;
import com.aft.monoproject.Spring.mono.project.dto.user.request.UpdateUserRequestDto;
import com.aft.monoproject.Spring.mono.project.entity.User;
import com.aft.monoproject.Spring.mono.project.exception.ApiError;
import com.aft.monoproject.Spring.mono.project.exception.ApiException;
import com.aft.monoproject.Spring.mono.project.repository.UserRepository;
import com.aft.monoproject.Spring.mono.project.utils.enums.Role;
import com.aft.monoproject.Spring.mono.project.utils.enums.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public User createUser(CreateUserRequestDto requestDto) {
        User user = requestDto.toUser();
        Optional<User> existingUserOpt = userRepository.findByEmail(user.getEmail());

        if (existingUserOpt.isPresent()) {
            throw new ApiException(ApiError.USER_EMAIL_EXISTS);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setState(UserState.NOT_VERIFIED);

        return userRepository.save(user);
    }

    public User updateUser(Integer userId, UpdateUserRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(ApiError.USER_NOT_FOUND));

        if (!user.getEmail().equalsIgnoreCase(requestDto.getEmail())) {
            userRepository.findByEmail(requestDto.getEmail()).ifPresent(existingUser -> {
                throw new ApiException(ApiError.USER_EMAIL_EXISTS);
            });
            user.setEmail(requestDto.getEmail());
            user.setState(UserState.NOT_VERIFIED);
            authService.initiateEmailUpdateVerification(user);
            authService.singOut(user.getId());
        }

        if (requestDto.getName() != null) {
            user.setName(requestDto.getName());
        }
        if (requestDto.getSurname() != null) {
            user.setSurname(requestDto.getSurname());
        }

        return userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseGet(() -> {
            throw new ApiException(ApiError.USER_NOT_FOUND);
        });
    }

    public User getAuthenticatedUserById(Integer userId, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ApiError.USER_NOT_FOUND));

        if (!user.getEmail().equalsIgnoreCase(email)) {
            throw new ApiException(ApiError.ACCESS_DENIED);
        }

        return user;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseGet(() -> {
            throw new ApiException(ApiError.USER_NOT_FOUND);
        });
    }
}
