package com.example.SomayaAssaadiExam.repositories;

import com.example.SomayaAssaadiExam.models.Role;
import com.example.SomayaAssaadiExam.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String role);

}
