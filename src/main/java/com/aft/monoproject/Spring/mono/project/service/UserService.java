package com.aft.monoproject.Spring.mono.project.service;

import com.aft.monoproject.Spring.mono.project.dto.user.request.CreateUserRequestDto;
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

    public void verifyUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(ApiError.USER_NOT_FOUND));

        user.setState(UserState.ACTIVE);
        userRepository.save(user);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseGet(() -> {
            throw new ApiException(ApiError.USER_NOT_FOUND);
        });
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseGet(() -> {
            throw new ApiException(ApiError.USER_NOT_FOUND);
        });
    }
}
