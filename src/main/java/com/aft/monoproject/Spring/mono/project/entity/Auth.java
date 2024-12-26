package com.aft.monoproject.Spring.mono.project.entity;

import com.aft.monoproject.Spring.mono.project.utils.enums.AuthType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@RedisHash("auth")
@Data
@EqualsAndHashCode
public class Auth implements Serializable {

    @Id
    private String id;
    private Integer userId;
    private String email;
    private Date expiresIn;
    private Date lastRequestDate;
    private String token;
    private String verificationCode;
    private AuthType authType;
    private int invalidTokenCount = 0;
}