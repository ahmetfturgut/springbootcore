package com.aft.monoproject.Spring.mono.project.exception;

import com.aft.monoproject.Spring.mono.project.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException exception) {
        String errorMessage = exception.getMessage();
        Integer errorCode = 500;

        // HTTP yanıt kodu 200 olarak ayarlanıyor
        return ResponseEntity
                .ok(ApiResponse.error(errorMessage, errorCode));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Object>> handleApiException(ApiException exception) {
        String errorMessage = exception.getMessage();
        Integer errorCode = exception.getApiError().getErrorCode();

        // HTTP yanıt kodu 200 olarak ayarlanıyor
        return ResponseEntity
                .ok(ApiResponse.error(errorMessage, errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        // HTTP yanıt kodu 200 olarak ayarlanıyor
        return ResponseEntity
                .ok(ApiResponse.validationError(errors));
    }
}
