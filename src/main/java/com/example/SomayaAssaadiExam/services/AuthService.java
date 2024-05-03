package com.example.SomayaAssaadiExam.services;

import com.example.SomayaAssaadiExam.models.Role;
import com.example.SomayaAssaadiExam.models.User;

public interface AuthService {

    String login(User user, String password);
    User register(User entity, Role admin);
    public String validateAccount(String email, String code);
    boolean deleteUser(Long userId);
    User updateUser(Long userId, User updatedUser);

}
