package com.aft.monoproject.Spring.mono.project.dto.user.response;

import com.aft.monoproject.Spring.mono.project.dto.auth.AuthendicatedUserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifySignInResponseDto {
    private String token;
    private AuthendicatedUserResponseDto authendicatedUserResponseDto;
}
