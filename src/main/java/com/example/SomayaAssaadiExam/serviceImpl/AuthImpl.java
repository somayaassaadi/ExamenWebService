package com.example.SomayaAssaadiExam.serviceImpl;
import com.example.SomayaAssaadiExam.models.Role;
import com.example.SomayaAssaadiExam.models.User;
import com.example.SomayaAssaadiExam.repositories.UserRepo;
import com.example.SomayaAssaadiExam.security.JwtService;
import com.example.SomayaAssaadiExam.services.AuthService;
import com.example.SomayaAssaadiExam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthImpl implements AuthService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepo userRepo;

    @Override
    public String login(User user, String password) {
        if(bCryptPasswordEncoder.matches(password, user.getPassword()))
            return jwtService.generateToken(user);
        return null;
    }


    @Override
    public User register(User entity, Role role) {
        String passwordEncoded = bCryptPasswordEncoder.encode(entity.getPassword());
        entity.setPassword(passwordEncoded);
        userService.addRoleToUser(entity, role);
        return userService.createUser(entity);
    }

    @Override
    public String validateAccount(String email, String code) {
        return null;
    }

    @Override
    public boolean deleteUser(Long userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            updatedUser.setId(userId);
            return userRepo.save(updatedUser);
        }
        return null;
    }





}

