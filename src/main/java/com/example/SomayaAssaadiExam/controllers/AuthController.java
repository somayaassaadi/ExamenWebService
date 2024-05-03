package com.example.SomayaAssaadiExam.controllers;


import com.example.SomayaAssaadiExam.models.Admin;
import com.example.SomayaAssaadiExam.models.Role;
import com.example.SomayaAssaadiExam.models.User;
import com.example.SomayaAssaadiExam.repositories.RoleRepo;
import com.example.SomayaAssaadiExam.serviceImpl.UserImpl;
import com.example.SomayaAssaadiExam.services.AuthService;
import com.example.SomayaAssaadiExam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Autowired
    RoleRepo roleRepo;


    private ResponseEntity<?> userExisteResponse (User entity){
        Optional<User> user = userService.getUSerByPseudo(entity.getEmail());
        if(user.isPresent())
            return new ResponseEntity<>(
                    "pseudo existe déjà",
                    HttpStatus.CONFLICT
            );
        return null;
    }

    @PostMapping("admin/signup")
    public ResponseEntity<?> amdinregister(@RequestBody User entity){
        ResponseEntity<?> res = userExisteResponse(entity);
        if (res != null)
            return res;
        Optional<Role> role = roleRepo.findByRoleName(Role.RoleEnum.ADMIN.name());
        if(role.isEmpty())
            return new ResponseEntity<>(
                    "Une erreur est servenue !",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        return new ResponseEntity<>(
                authService.register(entity, role.get()),
                HttpStatus.CREATED
        );
    }

    @PostMapping("admin/login")
    public ResponseEntity<?> loginadmin(@RequestBody Map<String, String> request){
        String email = request.get("email");
        String password = request.get("password");
        Optional<User> user = userService.getUSerByPseudo(email);
        if (user.isEmpty())
            return new ResponseEntity(
                    "User n'existe pas",
                    HttpStatus.NOT_FOUND
            );
        String jwt = authService.login(user.get(), password);
        if (jwt == null)
            return new ResponseEntity<>(
                    "Mot de passe incorrect",
                    HttpStatus.FORBIDDEN
            );
        return new ResponseEntity<>(
                jwt,
                HttpStatus.OK
        );
    }

    @PostMapping("users/signup")
    public ResponseEntity<?> userRegister(@RequestBody User entity){
        ResponseEntity<?> res = userExisteResponse(entity);
        if (res != null)
            return res;
        Optional<Role> role = roleRepo.findByRoleName(Role.RoleEnum.USER.name());
        if(role.isEmpty())
            return new ResponseEntity<>(
                    "Une erreur est servenue !",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        return new ResponseEntity<>(
                authService.register(entity, role.get()),
                HttpStatus.CREATED
        );
    }

    @PostMapping("users/login")
    public ResponseEntity<?> loginuser(@RequestBody Map<String, String> request){
        String fullName = request.get("fullName");
        String password = request.get("password");
        Optional<User> user = userService.getUSerByPseudo(fullName);
        if (user.isEmpty())
            return new ResponseEntity(
                    "User n'existe pas",
                    HttpStatus.NOT_FOUND
            );
        String jwt = authService.login(user.get(), password);
        if (jwt == null)
            return new ResponseEntity<>(
                    "Mot de passe incorrect",
                    HttpStatus.FORBIDDEN
            );
        return new ResponseEntity<>(
                jwt,
                HttpStatus.OK
        );
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        boolean deleted = authService.deleteUser(userId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User user = authService.updateUser(userId, updatedUser);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
