package com.example.todolist.TodoAPI.controller;

import com.example.todolist.TodoAPI.annotations.UserAuthenticated;
import com.example.todolist.TodoAPI.builders.FailureResponseBuilder;
import com.example.todolist.TodoAPI.dto.requests.TaskCreateRequest;
import com.example.todolist.TodoAPI.dto.response.TaskDetailsDto;
import com.example.todolist.TodoAPI.enums.ErrorCode;
import com.example.todolist.TodoAPI.exception.APIException;
import com.example.todolist.TodoAPI.service.SubTaskService;
import com.example.todolist.TodoAPI.service.TaskService;
import com.example.todolist.dao.pojo.Subtask;
import com.example.todolist.dao.pojo.Task;
import com.example.todolist.dao.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final SubTaskService subTaskService;

    public TaskController(TaskService taskService, SubTaskService subTaskService) {
        this.taskService = taskService;
        this.subTaskService = subTaskService;
    }

    @PostMapping
    @UserAuthenticated
    public ResponseEntity<TaskDetailsDto> createTask(HttpServletRequest request, @RequestBody TaskCreateRequest createRequest) {
        try {
            // ✅ Get user from request attributes (set by the filter)
            User user = (User) request.getAttribute("user");

            if (user == null) {
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
            }

            TaskDetailsDto taskDetailsDto = taskService.createTask(user, createRequest);
            return ResponseEntity.ok(taskDetailsDto);
        } catch (APIException e) {
            log.error("Bad request provided: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Unknown exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @POST
    @Path("/{task_id}/subtasks")
    @UserAuthenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSubtask(@Context ContainerRequestContext requestContext, @PathParam("task_id") String taskId, Subtask subtask) {
        try {
            User user = (User) requestContext.getProperty("user");
            Task updatedTask = subTaskService.addSubtask(taskId, subtask);
            return Response.ok().entity(updatedTask).build();
//        } catch (APIException e) {
//            log.error("Bad request provided {}", e.getMessage(), e);
//            return FailureResponseBuilder.buildFailureResponse(ErrorCode.INVALID_REQUEST);
        } catch (Exception e) {
            log.error("Unknown exception occurred: {}", e.getMessage(), e);
            return FailureResponseBuilder.buildFailureResponse(ErrorCode.UNKNOWN_SERVER_ERROR);
        }
    }

    @GET
    @Path("/tasks")
    @UserAuthenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUserTasks(@Context ContainerRequestContext requestContext, @QueryParam("status") String status) {
        try {
            User user = (User) requestContext.getProperty("user");
            TaskDetailsDto taskDetailsDto = taskService.getAllUserTasks(user, status);
            return Response.ok().entity(taskDetailsDto).build();
//        } catch (APIException e) {
//            log.error("Bad request provided {}", e.getMessage(), e);
//            return FailureResponseBuilder.buildFailureResponse(ErrorCode.INVALID_REQUEST);
        } catch (Exception e) {
            log.error("Got unknownException for createGame: {}", e.getMessage(), e);
            return FailureResponseBuilder.buildFailureResponse(ErrorCode.UNKNOWN_SERVER_ERROR);
        }
    }

    @PUT
    @Path("/")
    @UserAuthenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTask(@Context ContainerRequestContext requestContext, @PathParam("taskId") String taskId) {
        try {
            User user = (User) requestContext.getProperty("user");
            TaskDetailsDto taskDetailsDto = taskService.getTask(taskId);
            return Response.ok().entity(taskDetailsDto).build();
        } catch (APIException e) {
            log.error("Bad request provided {}", e.getMessage(), e);
            return FailureResponseBuilder.buildFailureResponse(ErrorCode.INVALID_REQUEST);
        } catch (Exception e) {
            log.error("Got unknownException for createGame: {}", e.getMessage(), e);
            return FailureResponseBuilder.buildFailureResponse(ErrorCode.UNKNOWN_SERVER_ERROR);
        }
    }

    @GET
    @Path("/")
    @UserAuthenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTask(@Context ContainerRequestContext requestContext, @PathParam("taskId") String taskId) {
        try {
            User user = (User) requestContext.getProperty("user");
            TaskDetailsDto taskDetailsDto = taskService.getTask(taskId);
            return Response.ok().entity(taskDetailsDto).build();
        } catch (APIException e) {
            log.error("Bad request provided {}", e.getMessage(), e);
            return FailureResponseBuilder.buildFailureResponse(ErrorCode.INVALID_REQUEST);
        } catch (Exception e) {
            log.error("Got unknownException for createGame: {}", e.getMessage(), e);
            return FailureResponseBuilder.buildFailureResponse(ErrorCode.UNKNOWN_SERVER_ERROR);
        }
    }
}
