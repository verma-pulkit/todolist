package com.example.todolist.TodoAPI.utils;

import com.example.todolist.TodoAPI.dto.requests.SubTaskCreateRequest;
import com.example.todolist.TodoAPI.dto.requests.TaskCreateRequest;
import com.example.todolist.TodoAPI.dto.response.SubTaskDetailsDto;
import com.example.todolist.TodoAPI.dto.response.TaskDetailsDto;
import com.example.todolist.TodoAPI.exception.APIException;
import com.example.todolist.dao.pojo.Subtask;
import com.example.todolist.dao.pojo.Task;
import com.example.todolist.dao.pojo.User;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class TaskUtils {
    public static Task createTaskFromRequest(User user, TaskCreateRequest createRequest) {
        return Task.builder()
                .taskId(createRequest.getTaskId())
                .priority(createRequest.getPriority())
                .dueDate(createRequest.getDueDate())
                .ownerId(user.getUserId())
                .createdTs(LocalDateTime.now())
                .build();
    }

    public static TaskDetailsDto createResponseFromTask(Task task, List<Subtask> subtaskList) {
        return TaskDetailsDto.builder()
                .taskId(task.getTaskId())
                .dueDate(task.getDueDate())
                .priority(task.getPriority())
                .progressPercentage(getProgressPercentageFromSubtasks(subtaskList))
                .subTaskDetailsDtoList(getSubTaskDetails(subtaskList))
                .build();
    }

    private static List<SubTaskDetailsDto> getSubTaskDetails(List<Subtask> subtaskList) {

    }

    private static double getProgressPercentageFromSubtasks(List<Subtask> subtaskList) {
        if (subtaskList == null || subtaskList.isEmpty()) {
            return 0.0; // No subtasks, progress is 0%
        }

        int total = subtaskList.size();
        long completedCount = subtaskList.stream().filter(Subtask::isCompleted).count();

        return (completedCount * 100.0) / total;
    }


    public static void validateCreateRequest(TaskCreateRequest createRequest) throws APIException {
        if (createRequest == null) {
            throw new APIException("Task creation request cannot be null.");
        }
        if (!StringUtils.hasText(createRequest.getAssigneeId())) {
            throw new APIException("Assignee ID is required.");
        }
        if (System.currentTimeMillis() < createRequest.getDueDate()) {
            throw new APIException("Due date cannot be in the past.");
        }
        validateTaskId(createRequest.getTaskId());
    }


    public static void validateTaskId(String taskId) throws APIException {
        if (StringUtils.hasText(taskId))
            throw new APIException("Task Id invalid");
    }

    public static void validateSubTaskCreateRequest(SubTaskCreateRequest createRequest) throws APIException {
        if (createRequest == null) {
            throw new APIException("CreateRequest cannot be null.");
        }
        if (!StringUtils.hasText(createRequest.getSubTaskId())) {
            throw new APIException("SubTask ID is required.");
        }
        if (!StringUtils.hasText(createRequest.getTaskId())) {
            throw new APIException("Task ID is required.");
        }
        if (createRequest.getStatus() == null) {
            throw new APIException("Status cannot be null.");
        }
    }

    public static Subtask createSubTaskFromRequest(User user, SubTaskCreateRequest createRequest) {
        return Subtask.builder()
                .subTaskId(createRequest.getSubTaskId())
                .taskId(createRequest.getTaskId())
                .build();
    }

    public static SubTaskDetailsDto createResponseFromSubTask(Subtask subtask) {
        return SubTaskDetailsDto.builder()
                .subTaskId(subtask.getSubTaskId())
                .taskId(subtask.getTaskId())
                .status(subtask.getStatus())
                .build();
    }

}
