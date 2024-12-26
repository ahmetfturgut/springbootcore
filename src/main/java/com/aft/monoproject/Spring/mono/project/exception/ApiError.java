package com.aft.monoproject.Spring.mono.project.exception;

import lombok.Getter;

import java.util.Date;

@Getter
public class ApiError {

    private final int errorCode;
    private final Date timestamp = new Date();
    private final String message;


    public ApiError(int statusCode, String message) {
        this.errorCode = statusCode;
        this.message = message;
    }

    //auth
    public static ApiError TOKEN_ERROR = new ApiError(101, "Token error.");
    public static ApiError TOKEN_EXPIRED = new ApiError(102, "Token expired.");
    public static ApiError TOKEN_BLOCKED = new ApiError(103, "Token blocked.");
    public static ApiError DEFERRABLE_TOKEN_EXPIRED = new ApiError(104, "Defferrable Token expired.");
    public static ApiError REQUIRED_TERMS_AND_CONDITIONS = new ApiError(105, "Terms and conditions is required.");
    public static ApiError TOKEN_CODE_ERROR = new ApiError(106, "Token code error.");
    public static ApiError WRONG_PHONE_OR_PASSWORD = new ApiError(107, "Wrong email or password.");
    public static ApiError INVALID_VERIFICATION_CODE = new ApiError(108, "Wrong verification code.");
    public static ApiError VERIFICATION_TOKEN_NOT_FOUND = new ApiError(109, "Verification token not found.");
    public static ApiError INVALID_CREDENTIALS = new ApiError(110, "Invalid credentials.");


    // USER
    public static ApiError USER_EXISTS = new ApiError(201, "User exists.");
    public static ApiError USER_PHONE_EXISTS = new ApiError(202, "User phone exists.");
    public static ApiError USER_NOT_FOUND = new ApiError(203, "User not found.");
    public static ApiError NOT_AUTHORIZED = new ApiError(204, "You are not authorized.");
    public static ApiError INVALID_USER_TYPE = new ApiError(205, "Invalid user type.");
    public static ApiError PASSWORDS_DO_NOT_MATCH = new ApiError(206, "password dont match.");
    public static ApiError USER_EMAIL_EXISTS = new ApiError(207, "User email exists.");


    // Cntent
    public static ApiError CONTENT_NOT_FOUND = new ApiError(303, "content not found.");

    // Farmer
    public static ApiError FARMER_NOT_FOUND = new ApiError(403, "Farmer not found.");

    // Merchant
    public static ApiError MERCHANT_NOT_FOUND = new ApiError(503, "Merchant not found.");


    // PRODUCT FinancialRecord
    public static ApiError PRODUCT_FINANCIAL_RECORD_NOT_FOUND = new ApiError(603, "ProductFinancialRecord not found.");
    public static ApiError INSUFFICIENT_QAUANTITY = new ApiError(604, "Insufficient product quantity for farmer.");


    // PRODUCT price
    public static ApiError PRODUCT_PRICE_NOT_FOUND = new ApiError(703, "Product price not found.");

}