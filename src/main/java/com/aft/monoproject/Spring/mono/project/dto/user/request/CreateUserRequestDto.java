package com.aft.monoproject.Spring.mono.project.dto.user.request;

import com.aft.monoproject.Spring.mono.project.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequestDto {
    @NotEmpty(message = "The full name is required.")
    @Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    private String name;

    @NotEmpty(message = "The full surname is required.")
    @Size(min = 2, max = 100, message = "The length of surname must be between 2 and 100 characters.")
    private String surname;

    @Size(min = 5, max = 100, message = "The length of email must be between 5 and 100 characters.")
    private String email;

    @NotEmpty(message = "The password is required.")
    @Size(min = 6, max = 12, message = "The length of full name must be between 6 and 12 characters.")
    private String password;

    public User toUser() {
        return User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .build();
    }
}
