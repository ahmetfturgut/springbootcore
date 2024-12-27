package com.aft.monoproject.Spring.mono.project.dto.user.response;

import com.aft.monoproject.Spring.mono.project.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String role;
    private String state;

    public static UserResponseDto fromUser(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .state(user.getState().toString())
                .build();
    }
}
