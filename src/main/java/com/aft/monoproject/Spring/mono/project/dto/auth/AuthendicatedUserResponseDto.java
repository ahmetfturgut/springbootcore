package com.aft.monoproject.Spring.mono.project.dto.auth;

import com.aft.monoproject.Spring.mono.project.utils.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthendicatedUserResponseDto {

    private Integer id;
    private String email;
    private String name;
    private String surname;
    private Role role;

}
