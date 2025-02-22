package com.example.todolist.dao.respository;

import com.example.todolist.dao.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserId(String userId);  // ✅ Find user by uid
}