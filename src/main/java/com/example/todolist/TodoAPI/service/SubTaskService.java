package com.example.todolist.TodoAPI.service;

import com.example.todolist.TodoAPI.dto.requests.SubTaskCreateRequest;
import com.example.todolist.TodoAPI.dto.response.SubTaskDetailsDto;
import com.example.todolist.TodoAPI.exception.APIException;
import com.example.todolist.dao.respository.SubTaskDao;
import com.example.todolist.dao.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.todolist.TodoAPI.utils.TaskUtils.*;

@Service
@Slf4j
public class SubTaskService {


    @Autowired
    private SubTaskDao subTaskDao;

    public SubTaskDetailsDto createSubTask(User user, SubTaskCreateRequest createRequest) throws APIException {
        validateSubTaskCreateRequest(createRequest);
        Optional<Subtask> existingTask = subTaskDao.findBySubTask(createRequest.getTaskId());
        if (existingTask.isPresent()) {
            return createResponseFromSubTask(existingTask.get());  // Return existing task to ensure idempotency
        }

        Subtask subtask = subTaskDao.saveSubTask(createSubTaskFromRequest(user, createRequest));
        return createResponseFromSubTask(subtask);
    }

    public SubTaskDetailsDto getTask(String taskId) throws APIException {
        validateTaskId(taskId);
        Optional<Subtask> existingTask = subTaskDao.findBySubTask(taskId);
        if (existingTask.isPresent()) {
            return createResponseFromSubTask(existingTask.get());  // Return existing task to ensure idempotency
        }
        throw new APIException("Task does not exist for taskId");
    }
}
