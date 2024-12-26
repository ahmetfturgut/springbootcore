package com.aft.monoproject.Spring.mono.project.dto.user.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddUserRequestDto {
    @NotEmpty
    private String phone;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

}
