package com.example.todolist.dao.respository;

import com.example.todolist.dao.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component  // ✅ Spring will auto-detect this as a Bean
public class ApplicationUserDao {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationUserDao.class);

    private final ApplicationUserRepository userRepository;


    @Autowired
    public ApplicationUserDao(ApplicationUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);

        if (user.isPresent()) {
            logger.info("User found in database: {}", user.get());
        } else {
            logger.warn("User with userId={} not found in database", userId);
        }

        return user.orElse(null);
    }
}