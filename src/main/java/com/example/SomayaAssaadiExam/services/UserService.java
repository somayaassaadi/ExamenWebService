package com.example.SomayaAssaadiExam.services;

import com.example.SomayaAssaadiExam.models.Admin;
import com.example.SomayaAssaadiExam.models.Role;
import com.example.SomayaAssaadiExam.models.User;

import java.util.Optional;



public interface UserService {

    Optional<User> getAdminByEmail(String email);
    Optional<User> getUserByFullName(String fullName);
    void addRoleToUser(User user, Role role);
    User createUser(User entity);






}
