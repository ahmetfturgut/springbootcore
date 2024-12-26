package com.aft.monoproject.Spring.mono.project.dto.user.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ForgotPasswordRequestDto {

    @NotEmpty(message = "The phone is required.")
    @Size(min = 10, max = 10, message = "The length of phone must be 10 characters.")
    private String phone;
}
