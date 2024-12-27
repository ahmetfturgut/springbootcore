package com.aft.monoproject.Spring.mono.project.dto.user.request;

import lombok.Data;

@Data
public class VerifyPasswordResetRequestDto {
    private String token;
    private String verificationCode;
    private String newPassword;
}
