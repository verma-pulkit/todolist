package com.example.todolist.dao.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;   // MongoDB auto-generated ID
    private String userId;
    private String token;
}
