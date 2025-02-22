package com.example.todolist.TodoAPI.exception;

import com.example.todolist.TodoAPI.enums.ErrorCode;

public class APIException extends Exception {
    protected ErrorCode errorCode;
    protected String errorMessage;
    protected String extraInfo;
    protected String extraInfoForServer;

    public APIException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    public APIException(ErrorCode errorCode, String errorMessage) {
        super(errorCode.name());
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public APIException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = ErrorCode.UNKNOWN_SERVER_ERROR;
    }

    public APIException(ErrorCode errorCode, String errorMessage, String extraInfo) {
        super(errorCode.name());
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.extraInfo = extraInfo;
    }

    public APIException(ErrorCode errorCode, String errorMessage, String extraInfo, String extraInfoForServer) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.extraInfo = extraInfo;
        this.extraInfoForServer = extraInfoForServer;
    }

    public APIException() {
        super();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public String getExtraInfoForServer() {
        return extraInfoForServer;
    }

    public ErrorCode getErrorCode() {
        if (errorCode == null) {
            return ErrorCode.UNKNOWN_SERVER_ERROR;
        }
        return errorCode;
    }
}
