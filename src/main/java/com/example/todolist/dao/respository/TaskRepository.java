package com.example.todolist.dao.respository;

import com.example.todolist.dao.pojo.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    Optional<Task> findByTaskId(String taskId);  // ✅ Find task by taskId
}