package com.example.todolist.TodoAPI.dto.response;

import com.example.todolist.dao.pojo.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubTaskDetailsDto {
    private String subTaskId;
    private String taskId;
    private Status status;
}
