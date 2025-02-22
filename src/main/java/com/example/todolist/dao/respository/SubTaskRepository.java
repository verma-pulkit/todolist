package com.example.todolist.dao.respository;

import com.example.todolist.dao.pojo.Subtask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubTaskRepository extends MongoRepository<Subtask, String> {

    Optional<Subtask> findBySubTaskId(String subTaskId);  // ✅ Find subtask by subTaskId

    List<Subtask> findByTaskId(String taskId);  // ✅ Find all subtasks for a given taskId
}