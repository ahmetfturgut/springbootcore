//package com.aft.monoproject.Spring.mono.project.controller;
//
//import com.aft.monoproject.Spring.mono.project.config.JwtService;
//import com.aft.monoproject.Spring.mono.project.dto.ApiResponse;
//import com.aft.monoproject.Spring.mono.project.dto.auth.AuthendicatedUserResponseDto;
//import com.aft.monoproject.Spring.mono.project.dto.user.request.*;
//import com.aft.monoproject.Spring.mono.project.dto.user.response.CreateUserResponseDto;
//import com.aft.monoproject.Spring.mono.project.dto.user.response.VerifySignInResponseDto;
//import com.aft.monoproject.Spring.mono.project.entity.Auth;
//import com.aft.monoproject.Spring.mono.project.entity.User;
//import com.aft.monoproject.Spring.mono.project.exception.ApiError;
//import com.aft.monoproject.Spring.mono.project.exception.ApiException;
//import com.aft.monoproject.Spring.mono.project.properties.AuthConfigProperties;
//import com.aft.monoproject.Spring.mono.project.repository.AuthRepository;
//import com.aft.monoproject.Spring.mono.project.service.AuthService;
//import com.aft.monoproject.Spring.mono.project.service.UserService;
// import com.aft.monoproject.Spring.mono.project.utils.enums.Role;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class AuthControllerTest {
//
//    @Mock
//    private AuthService authService;
//
//    @InjectMocks
//    private AuthController authController;
//
//
//    public AuthControllerTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    //TODO: signup test method
//
//    @Test
//    void testVerifySignup_Success() {
//        VerifySignUpRequestDto requestDto = new VerifySignUpRequestDto();
//        requestDto.setToken("mockToken");
//        requestDto.setCode("123456");
//
//        doNothing().when(authService).verifySignUp(requestDto.getToken(), requestDto.getCode());
//
//        ApiResponse<String> response = authController.verifySignUp(requestDto);
//
//        assertNotNull(response);
//        assertTrue(response.isSuccess());
//        assertEquals("User sign-up successfully verified.", response.getData());
//
//        verify(authService, times(1)).verifySignUp(requestDto.getToken(), requestDto.getCode());
//    }
//
//    @Test
//    void testVerifySignup_InvalidCode() {
//        VerifySignUpRequestDto requestDto = new VerifySignUpRequestDto();
//        requestDto.setToken("mockToken");
//        requestDto.setCode("invalidCode");
//
//        doThrow(new ApiException(ApiError.INVALID_VERIFICATION_CODE)).when(authService).verifySignUp(requestDto.getToken(), requestDto.getCode());
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.verifySignUp(requestDto);
//        });
//
//        assertEquals(ApiError.INVALID_VERIFICATION_CODE, exception.getApiError());
//
//        verify(authService, times(1)).verifySignUp(requestDto.getToken(), requestDto.getCode());
//    }
//
//    @Test
//    void testVerifySignup_ExpiredToken() {
//        VerifySignUpRequestDto requestDto = new VerifySignUpRequestDto();
//        requestDto.setToken("expiredToken");
//        requestDto.setCode("123456");
//
//        doThrow(new ApiException(ApiError.TOKEN_EXPIRED)).when(authService).verifySignUp(requestDto.getToken(), requestDto.getCode());
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.verifySignUp(requestDto);
//        });
//
//        assertEquals(ApiError.TOKEN_EXPIRED, exception.getApiError());
//
//        verify(authService, times(1)).verifySignUp(requestDto.getToken(), requestDto.getCode());
//    }
//
//    @Test
//    void testVerifyEmailUpdate_Success() {
//        VerifyEmailUpdateRequestDto requestDto = new VerifyEmailUpdateRequestDto();
//        requestDto.setToken("validToken");
//        requestDto.setVerificationCode("123456");
//
//        doNothing().when(authService).verifyEmailUpdate(requestDto.getToken(), requestDto.getVerificationCode());
//
//        ApiResponse<String> response = authController.verifyEmailUpdate(requestDto);
//
//        assertNotNull(response);
//        assertTrue(response.isSuccess());
//        assertEquals("Email successfully verified.", response.getData());
//
//        verify(authService, times(1)).verifyEmailUpdate(requestDto.getToken(), requestDto.getVerificationCode());
//    }
//
//    @Test
//    void testVerifyEmailUpdate_InvalidCode() {
//        VerifyEmailUpdateRequestDto requestDto = new VerifyEmailUpdateRequestDto();
//        requestDto.setToken("validToken");
//        requestDto.setVerificationCode("invalidCode");
//
//        doThrow(new ApiException(ApiError.INVALID_VERIFICATION_CODE)).when(authService).verifyEmailUpdate(requestDto.getToken(), requestDto.getVerificationCode());
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.verifyEmailUpdate(requestDto);
//        });
//
//        assertEquals(ApiError.INVALID_VERIFICATION_CODE, exception.getApiError());
//
//        verify(authService, times(1)).verifyEmailUpdate(requestDto.getToken(), requestDto.getVerificationCode());
//    }
//
//    @Test
//    void testVerifyEmailUpdate_ExpiredToken() {
//        VerifyEmailUpdateRequestDto requestDto = new VerifyEmailUpdateRequestDto();
//        requestDto.setToken("expiredToken");
//        requestDto.setVerificationCode("123456");
//
//        doThrow(new ApiException(ApiError.TOKEN_EXPIRED)).when(authService).verifyEmailUpdate(requestDto.getToken(), requestDto.getVerificationCode());
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.verifyEmailUpdate(requestDto);
//        });
//
//        assertEquals(ApiError.TOKEN_EXPIRED, exception.getApiError());
//
//        verify(authService, times(1)).verifyEmailUpdate(requestDto.getToken(), requestDto.getVerificationCode());
//    }
//
//    @Test
//    void testVerifyEmailUpdate_UserNotFound() {
//        VerifyEmailUpdateRequestDto requestDto = new VerifyEmailUpdateRequestDto();
//        requestDto.setToken("validToken");
//        requestDto.setVerificationCode("123456");
//
//        doThrow(new ApiException(ApiError.USER_NOT_FOUND)).when(authService).verifyEmailUpdate(requestDto.getToken(), requestDto.getVerificationCode());
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.verifyEmailUpdate(requestDto);
//        });
//
//
//        assertEquals(ApiError.USER_NOT_FOUND, exception.getApiError());
//
//        verify(authService, times(1)).verifyEmailUpdate(requestDto.getToken(), requestDto.getVerificationCode());
//    }
//
//    @Test
//    void testSignin_Success() {
//        SignInRequestDto requestDto = new SignInRequestDto();
//        requestDto.setEmail("test@example.com");
//        requestDto.setPassword("password123");
//
//        VerifySignInResponseDto responseDto = VerifySignInResponseDto.builder()
//                .token("mockToken")
//                .authendicatedUserResponseDto(
//                        AuthendicatedUserResponseDto.builder()
//                                .id(1)
//                                .email("test@example.com")
//                                .name("John")
//                                .surname("Doe")
//                                .role(Role.USER)
//                                .build()
//                )
//                .build();
//
//        when(authService.signin(requestDto.toUser())).thenReturn(responseDto);
//
//        ApiResponse<VerifySignInResponseDto> response = authController.signin(requestDto);
//
//        assertNotNull(response);
//        assertTrue(response.isSuccess());
//        assertEquals("mockToken", response.getData().getToken());
//        assertEquals("test@example.com", response.getData().getAuthendicatedUserResponseDto().getEmail());
//
//        verify(authService, times(1)).signin(requestDto.toUser());
//    }
//
//    @Test
//    void testSignin_InvalidPassword() {
//        SignInRequestDto requestDto = new SignInRequestDto();
//        requestDto.setEmail("test@example.com");
//        requestDto.setPassword("wrongPassword");
//
//        doThrow(new ApiException(ApiError.INVALID_CREDENTIALS))
//                .when(authService).signin(requestDto.toUser());
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.signin(requestDto);
//        });
//
//        assertEquals(ApiError.INVALID_CREDENTIALS, exception.getApiError());
//
//        verify(authService, times(1)).signin(requestDto.toUser());
//    }
//
//    @Test
//    void testSignin_UserNotFound() {
//        SignInRequestDto requestDto = new SignInRequestDto();
//        requestDto.setEmail("unknown@example.com");
//        requestDto.setPassword("password123");
//
//        doThrow(new ApiException(ApiError.USER_NOT_FOUND))
//                .when(authService).signin(requestDto.toUser());
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.signin(requestDto);
//        });
//
//        assertEquals(ApiError.USER_NOT_FOUND, exception.getApiError());
//
//        verify(authService, times(1)).signin(requestDto.toUser());
//    }
//
//    @Test
//    void testSignin_UserNotActive() {
//        SignInRequestDto requestDto = new SignInRequestDto();
//        requestDto.setEmail("inactive@example.com");
//        requestDto.setPassword("password123");
//
//        doThrow(new ApiException(ApiError.WRONG_PHONE_OR_PASSWORD))
//                .when(authService).signin(requestDto.toUser());
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.signin(requestDto);
//        });
//
//        assertEquals(ApiError.WRONG_PHONE_OR_PASSWORD, exception.getApiError());
//
//        verify(authService, times(1)).signin(requestDto.toUser());
//    }
//
//    @Test
//    void testForgotPassword_Success() {
//        ForgotPasswordRequestDto requestDto = new ForgotPasswordRequestDto();
//        requestDto.setEmail("test@example.com");
//
//        doNothing().when(authService).initiatePasswordReset(requestDto.getEmail());
//
//        ApiResponse<String> response = authController.forgotPassword(requestDto);
//
//        assertNotNull(response);
//        assertTrue(response.isSuccess());
//        assertEquals("Password reset initiated. Check your email for the verification code.", response.getData());
//
//        verify(authService, times(1)).initiatePasswordReset(requestDto.getEmail());
//    }
//
//    @Test
//    void testForgotPassword_UserNotFound() {
//        ForgotPasswordRequestDto requestDto = new ForgotPasswordRequestDto();
//        requestDto.setEmail("unknown@example.com");
//
//        doThrow(new ApiException(ApiError.USER_NOT_FOUND))
//                .when(authService).initiatePasswordReset(requestDto.getEmail());
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.forgotPassword(requestDto);
//        });
//
//        assertEquals(ApiError.USER_NOT_FOUND, exception.getApiError());
//
//        verify(authService, times(1)).initiatePasswordReset(requestDto.getEmail());
//    }
//
//    @Test
//    void testVerifyPasswordReset_Success() {
//        VerifyPasswordResetRequestDto requestDto = new VerifyPasswordResetRequestDto();
//        requestDto.setToken("validToken");
//        requestDto.setVerificationCode("123456");
//        requestDto.setNewPassword("newPassword123");
//
//        doNothing().when(authService).verifyPasswordReset(
//                requestDto.getToken(),
//                requestDto.getVerificationCode(),
//                requestDto.getNewPassword()
//        );
//
//        ApiResponse<String> response = authController.verifyPasswordReset(requestDto);
//
//        assertNotNull(response);
//        assertTrue(response.isSuccess());
//        assertEquals("Password reset successfully verified.", response.getData());
//
//        verify(authService, times(1)).verifyPasswordReset(
//                requestDto.getToken(),
//                requestDto.getVerificationCode(),
//                requestDto.getNewPassword()
//        );
//    }
//
//    @Test
//    void testVerifyPasswordReset_InvalidCode() {
//        VerifyPasswordResetRequestDto requestDto = new VerifyPasswordResetRequestDto();
//        requestDto.setToken("validToken");
//        requestDto.setVerificationCode("invalidCode");
//        requestDto.setNewPassword("newPassword123");
//
//        doThrow(new ApiException(ApiError.INVALID_VERIFICATION_CODE))
//                .when(authService).verifyPasswordReset(
//                        requestDto.getToken(),
//                        requestDto.getVerificationCode(),
//                        requestDto.getNewPassword()
//                );
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.verifyPasswordReset(requestDto);
//        });
//
//        assertEquals(ApiError.INVALID_VERIFICATION_CODE, exception.getApiError());
//
//        verify(authService, times(1)).verifyPasswordReset(
//                requestDto.getToken(),
//                requestDto.getVerificationCode(),
//                requestDto.getNewPassword()
//        );
//    }
//
//    @Test
//    void testVerifyPasswordReset_ExpiredToken() {
//        VerifyPasswordResetRequestDto requestDto = new VerifyPasswordResetRequestDto();
//        requestDto.setToken("expiredToken");
//        requestDto.setVerificationCode("123456");
//        requestDto.setNewPassword("newPassword123");
//
//        doThrow(new ApiException(ApiError.TOKEN_EXPIRED))
//                .when(authService).verifyPasswordReset(
//                        requestDto.getToken(),
//                        requestDto.getVerificationCode(),
//                        requestDto.getNewPassword()
//                );
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.verifyPasswordReset(requestDto);
//        });
//
//        assertEquals(ApiError.TOKEN_EXPIRED, exception.getApiError());
//
//        verify(authService, times(1)).verifyPasswordReset(
//                requestDto.getToken(),
//                requestDto.getVerificationCode(),
//                requestDto.getNewPassword()
//        );
//    }
//
//    @Test
//    void testVerifyPasswordReset_UserNotFound() {
//        VerifyPasswordResetRequestDto requestDto = new VerifyPasswordResetRequestDto();
//        requestDto.setToken("validToken");
//        requestDto.setVerificationCode("123456");
//        requestDto.setNewPassword("newPassword123");
//
//        doThrow(new ApiException(ApiError.USER_NOT_FOUND))
//                .when(authService).verifyPasswordReset(
//                        requestDto.getToken(),
//                        requestDto.getVerificationCode(),
//                        requestDto.getNewPassword()
//                );
//
//        ApiException exception = assertThrows(ApiException.class, () -> {
//            authController.verifyPasswordReset(requestDto);
//        });
//
//        assertEquals(ApiError.USER_NOT_FOUND, exception.getApiError());
//
//        verify(authService, times(1)).verifyPasswordReset(
//                requestDto.getToken(),
//                requestDto.getVerificationCode(),
//                requestDto.getNewPassword()
//        );
//    }
//
//
//}
