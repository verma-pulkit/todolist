package com.example.todolist.TodoAPI.builders;

import com.example.todolist.TodoAPI.enums.ErrorCode;
import com.example.todolist.TodoAPI.exception.APIException;

import lombok.Data;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by siddharth on 03/10/16.
 */

@Data
public class FailureResponseBuilder {
    private ErrorCode errorCode;
    private String errorMessage;
    private String extraInfo;

    private FailureResponseBuilder(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    private FailureResponseBuilder(ErrorCode errorCode, String errorMessage, String extraInfo) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.extraInfo = extraInfo;
    }

    public static Response buildFailureResponse(ErrorCode errorCode) {
        return Response.status(errorCode.getStatusCode())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .entity(new FailureResponseBuilder(errorCode)).build();
    }

    public static Response buildFailureResponse(ErrorCode errorCode, String errorMessage) {
        return Response.status(errorCode.getStatusCode())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .entity(new FailureResponseBuilder(errorCode, errorMessage, null)).build();
    }

    public static Response buildFailureResponse(APIException e) {
        return Response.status(e.getErrorCode().getStatusCode())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .entity(new FailureResponseBuilder(e.getErrorCode(), e.getErrorMessage(), e.getExtraInfo())).build();
    }
}
