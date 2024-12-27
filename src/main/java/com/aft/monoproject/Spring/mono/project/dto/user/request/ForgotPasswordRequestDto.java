package com.aft.monoproject.Spring.mono.project.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ForgotPasswordRequestDto {

    @Email(message = "Invalid email format")
    private String email;
}
