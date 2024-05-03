package com.example.SomayaAssaadiExam.services;

import com.example.SomayaAssaadiExam.models.Role;
import com.example.SomayaAssaadiExam.models.User;

import java.util.Optional;



public interface UserService {

    Optional<User> getUSerByPseudo(String pseudo);
    Optional<User> getUSerByEmail(String email);
    void addRoleToUser(User user, Role role);
    User createUser(User entity);



}
