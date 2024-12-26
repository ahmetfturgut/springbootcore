package com.aft.monoproject.Spring.mono.project.dto.user.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifySignUpRequestDto {

    @NotEmpty(message = "The token is required.")
    private String token;

    @NotEmpty(message = "The code is required.")
    private String code;
}
