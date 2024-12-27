package com.aft.monoproject.Spring.mono.project.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "auth.config")
public class AuthConfigProperties {
    private String jwtSecret;
    private Integer verifyEmailExpiresIn;
    private Integer verifySignUpExpiresIn;
    private Integer verifySignInExpiresIn;
    private Integer verifyPasswordExpiresIn;
    private Integer authExpiresIn;
}
