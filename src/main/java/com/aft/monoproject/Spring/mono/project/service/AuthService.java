package com.aft.monoproject.Spring.mono.project.service;

import com.aft.monoproject.Spring.mono.project.config.JwtService;
import com.aft.monoproject.Spring.mono.project.dto.auth.AuthendicatedUserResponseDto;
import com.aft.monoproject.Spring.mono.project.dto.user.response.VerifySignInResponseDto;
import com.aft.monoproject.Spring.mono.project.entity.Auth;
import com.aft.monoproject.Spring.mono.project.entity.User;
import com.aft.monoproject.Spring.mono.project.exception.ApiError;
import com.aft.monoproject.Spring.mono.project.exception.ApiException;
import com.aft.monoproject.Spring.mono.project.properties.AuthConfigProperties;
import com.aft.monoproject.Spring.mono.project.repository.AuthRepository;
import com.aft.monoproject.Spring.mono.project.repository.UserRepository;
import com.aft.monoproject.Spring.mono.project.utils.enums.AuthType;
import com.aft.monoproject.Spring.mono.project.utils.enums.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthConfigProperties authConfigProperties;

    public Auth signup(User user) {
        String code = generateVerificationCode();
        String token = jwtService.generateToken(user, AuthType.SIGNUP, authConfigProperties.getVerifySignUpExpiresIn());

        Auth auth = createAuth(user, token, AuthType.SIGNUP, authConfigProperties.getVerifySignUpExpiresIn(), code);

        return authRepository.save(auth);
    }

    public void verifySignUp(String token, String verificationCode) {

        String userEmail = jwtService.extractUsername(token);
        User user = userService.getUserByEmail(userEmail);

        Auth auth = authRepository.findById("userId:" + user.getId() + "authType:" + AuthType.SIGNUP)
                .orElseThrow(() -> new ApiException(ApiError.USER_NOT_FOUND));

        if (!auth.getVerificationCode().equals(verificationCode)) {
            throw new ApiException(ApiError.INVALID_VERIFICATION_CODE);
        }

        if (auth.getExpiresIn().before(new Date())) {
            throw new ApiException(ApiError.TOKEN_EXPIRED);
        }

        userService.verifyUser(auth.getUserId());

        authRepository.delete(auth);
    }

    public VerifySignInResponseDto signin(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new ApiException(ApiError.USER_NOT_FOUND));

        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new ApiException(ApiError.INVALID_CREDENTIALS);
        }

        if (!existingUser.getState().equals(UserState.ACTIVE)) {
            throw new ApiException(ApiError.WRONG_PHONE_OR_PASSWORD);
        }
        authRepository.findById("userId:" + user.getId() + "authType:" + AuthType.SIGNIN).ifPresent(authRepository::delete);

        String token = jwtService.generateToken(existingUser, AuthType.SIGNIN, authConfigProperties.getVerifySignInExpiresIn());

        Auth signInAuth = createAuth(existingUser, token, AuthType.SIGNIN, authConfigProperties.getVerifySignInExpiresIn(), null);
        authRepository.save(signInAuth);

        AuthendicatedUserResponseDto authenticatedUser = AuthendicatedUserResponseDto.builder()
                .id(existingUser.getId())
                .email(existingUser.getEmail())
                .name(existingUser.getName())
                .surname(existingUser.getSurname())
                .role(existingUser.getRole())
                .build();

        return VerifySignInResponseDto.builder()
                .token(token)
                .authendicatedUserResponseDto(authenticatedUser)
                .build();
    }

    private Auth createAuth(User user, String token, AuthType authType, long expiresIn, String verificationCode) {
        Auth auth = new Auth();
        auth.setId(generateAuthId(user, authType));
        auth.setUserId(user.getId());
        auth.setEmail(user.getEmail());
        auth.setExpiresIn(new Date(System.currentTimeMillis() + expiresIn));
        auth.setToken(token);
        auth.setAuthType(authType);
        auth.setVerificationCode(verificationCode);
        return auth;
    }

    private String generateAuthId(User user, AuthType authType) {
        return "userId:" + user.getId() + "authType:" + authType;
    }

    public String generateVerificationCode() {
        Random random = new Random();
        int min = 100000;
        int max = 1000000;
        int verificationCode = random.nextInt(max - min) + min;
        return String.valueOf(verificationCode);
    }
}
