package com.example.todolist.dao.respository;

import com.example.todolist.dao.pojo.Subtask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component  // ✅ Spring will auto-detect this as a Bean
public class SubTaskDao {

    private final SubTaskRepository subTaskRepository;

    @Autowired
    public SubTaskDao(SubTaskRepository subTaskRepository) {
        this.subTaskRepository = subTaskRepository;
    }

    public Optional<Subtask> findBySubTask(String subTaskId) {
        return subTaskRepository.findBySubTaskId(subTaskId);
    }

    public Subtask saveSubTask(Subtask subTaskFromRequest) {
        return subTaskRepository.save(subTaskFromRequest);  //
    }

    public List<Subtask> findBySubTasksByTaskId(String taskId) {
        return subTaskRepository.findByTaskId(taskId);  //
    }
}