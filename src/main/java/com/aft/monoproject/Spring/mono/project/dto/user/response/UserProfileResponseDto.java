package com.aft.monoproject.Spring.mono.project.dto.user.response;

 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponseDto {
    private Integer id;
    private String username;
    private String surname;
    private String phone;
 }
