package com.example.todolist.dao.respository;

import com.example.todolist.dao.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ApplicationUserDao {

    private final ApplicationUserRepository userRepository;

    @Autowired
    public ApplicationUserDao(ApplicationUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);

        if (user.isPresent()) {
            System.out.println("User Found in DB: " + user.get());
        } else {
            System.out.println("User with userId=" + userId + " NOT FOUND in DB");
        }

        return user.orElse(null);  // ✅ Return user if found, otherwise null
    }
}