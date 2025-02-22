package com.example.todolist.TodoAPI.enums;

public enum ErrorCode {
    AUTHENTICATION_ERROR(401),
    INVALID_OTP_ERROR(403),
    UNKNOWN_SERVER_ERROR(500),
    RESOURCE_NOT_FOUND(404),
    USER_NOT_FOUND(404),
    PAGE_NOT_FOUND(403),
    INVALID_USER(403),
    INVALID_REQUEST(400),
    INVALID_FILE(400),
    FORBIDDEN(403),
    DEBIT_LIMIT_REACHED(400),
    INSUFFICIENT_FUNDS(432),
    REDEMPTION_FAILED(400),
    INVALID_MSISDN_PASSED(400),
    MINIMUM_REDEEMABLE_AMOUNT_VIOLATED(400),
    PAYTM_WALLET_NOT_REGISTERED(400),
    PAYTM_KYC_REQUIRED(400),
    DUPLICATE_VIP_PRO_PURCHASED(400),
    NOT_ELIGIBLE_VIP_PRO(400),
    PAYTM_WALLET_ALREADY_USED(400),
    PAYTM_ERROR(400),
    NON_REGISTERED_MSISDN_USED(400),
    INVALID_NOTIFICATION(400),
    BAD_REQUEST(400),
    TOO_MANY_RETRIES(429),
    ID_CONFLICT(409),
    BLOCKED_USER(423),
    EXTERNAL_AUTH_ERROR(403),
    PRECONDITION_FAILED(412),
    LOGIN_NOT_ALLOWED(480);

    int statusCode;

    ErrorCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static ErrorCode fromStatusCode(int statusCode) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getStatusCode() == statusCode) {
                return errorCode;
            }
        }
        return null;
    }
}
