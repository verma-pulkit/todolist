package com.example.todolist.TodoAPI.dto.response;

public class GenericResponse {
    private boolean success;
    private Object data;


    public GenericResponse() {
        success = true;
    }

    public GenericResponse(Object data) {
        this();
        this.data = data;
    }

    public GenericResponse(boolean success) {
        this.success = success;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
