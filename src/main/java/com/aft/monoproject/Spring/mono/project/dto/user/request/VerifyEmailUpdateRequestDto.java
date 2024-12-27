package com.aft.monoproject.Spring.mono.project.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VerifyEmailUpdateRequestDto {
    @NotNull
    private String token;
    @NotNull
    private String verificationCode;
}