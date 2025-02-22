package com.example.todolist.TodoAPI.annotations;

import com.example.todolist.dao.respository.ApplicationUserDao;
import com.example.todolist.dao.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component  // ✅ Registers this filter as a Spring Bean
public class UserAuthenticatedRequestFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticatedRequestFilter.class);

    private final ApplicationUserDao appUserDao;

    public UserAuthenticatedRequestFilter(ApplicationUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Retrieve `uid` and `token` from cookies
        String uid = getCookieValue(request, "uid");
        String token = getCookieValue(request, "token");

        logger.debug("UID received: {}", uid);

        if (uid == null || token == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing UID or Token");
            return;
        }

        User user = appUserDao.findUserByUserId(uid);
        if (user == null || !token.equals(user.getToken())) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid UID or Token");
            return;
        }

        // ✅ Set user in request attributes so controllers can access it
        request.setAttribute("user", user);
        filterChain.doFilter(request, response);
    }

    // Helper method to retrieve cookies
    private String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}