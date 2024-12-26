package com.aft.monoproject.Spring.mono.project.dto.user.request;

 import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateUserRequestDto {

    @NotNull(message = "The id is required.")
    private Integer id;

    @NotEmpty(message = "The full name is required.")
    @Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
    private String name;

    @NotEmpty(message = "The full surname is required.")
    @Size(min = 2, max = 100, message = "The length of full surname must be between 2 and 100 characters.")
    private String surname;

    @NotEmpty(message = "The phone is required.")
    @Size(min = 10, max = 10, message = "The length of phone must be 10 characters.")
    private String phone;

    @NotEmpty(message = "The password is required.")
    @Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
    private String password;


}
