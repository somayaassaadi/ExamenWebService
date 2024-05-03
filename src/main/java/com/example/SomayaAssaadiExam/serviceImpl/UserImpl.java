package com.example.SomayaAssaadiExam.serviceImpl;


import com.example.SomayaAssaadiExam.models.Role;
import com.example.SomayaAssaadiExam.models.User;
import com.example.SomayaAssaadiExam.repositories.UserRepo;
import com.example.SomayaAssaadiExam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImpl implements UserService {


    @Autowired
    UserRepo userRepo;

    @Override
    public Optional<User> getUSerByPseudo(String pseudo) {
        return userRepo.findByPseudo(pseudo);
    }

    @Override
    public Optional<User> getUSerByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void addRoleToUser(User user, Role role){
        user.addRole(role);
        userRepo.save(user);
    }

    @Override
    public User createUser(User entity) {
        return userRepo.save(entity);
    }
}
