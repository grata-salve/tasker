package com.example.tasker.security.utils;

import com.example.tasker.domain.exception.GlobalException;
import com.example.tasker.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (User) principal;
        }
        throw new GlobalException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
}
