package com.example.todolist.TodoAPI.service;

import com.example.todolist.TodoAPI.dto.requests.TaskCreateRequest;
import com.example.todolist.TodoAPI.dto.response.TaskDetailsDto;
import com.example.todolist.TodoAPI.exception.APIException;
import com.example.todolist.dao.respository.SubTaskDao;
import com.example.todolist.dao.respository.TaskDao;
import com.example.todolist.dao.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.todolist.TodoAPI.utils.TaskUtils.*;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private SubTaskDao subTaskDao;

    public TaskDetailsDto createTask(User user, TaskCreateRequest createRequest) throws APIException {
        validateCreateRequest(createRequest);
        Optional<Task> existingTask = taskDao.findByTaskId(createRequest.getTaskId());
        if (existingTask.isPresent()) {
            List<Subtask> subtaskList = subTaskDao.findBySubTasksByTaskId(createRequest.getTaskId());
            return createResponseFromTask(existingTask.get(), subtaskList);  // Return existing task to ensure idempotency
        }

        Task task = taskDao.saveTask(createTaskFromRequest(user, createRequest));
        return createResponseFromTask(task, new ArrayList<>());
    }

    public TaskDetailsDto getTask(String taskId) throws APIException {
        validateTaskId(taskId);
        Optional<Task> existingTask = taskDao.findByTaskId(taskId);
        if (existingTask.isPresent()) {
            List<Subtask> subtaskList = subTaskDao.findBySubTasksByTaskId(taskId);
            return createResponseFromTask(existingTask.get(), subtaskList);  // Return existing task to ensure idempotency
        }
        throw new APIException("Task does not exist for taskId");
    }

    public TaskDetailsDto getAllUserTasks(User user, String status) {
        return null;
    }
}
