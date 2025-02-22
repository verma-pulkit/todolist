package com.example.todolist.dao.respository;

import com.example.todolist.dao.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component  // ✅ Spring will auto-detect this as a Bean
public class TaskDao {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskDao(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);  // ✅ Save task to MongoDB
    }

    public Optional<Task> findByTaskId(String taskId) {
        return taskRepository.findByTaskId(taskId);  // ✅ Fetch task by taskId
    }
}