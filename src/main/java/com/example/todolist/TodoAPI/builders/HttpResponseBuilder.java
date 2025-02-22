package com.example.todolist.TodoAPI.builders;


import com.example.todolist.TodoAPI.dto.response.GenericResponse;

import javax.ws.rs.core.Response;

public class HttpResponseBuilder {
    private HttpResponseBuilder() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static Response generateResponse(GenericResponse genericResponse) {
        return Response.ok().entity(genericResponse).build();
    }
}
