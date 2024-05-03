package com.example.SomayaAssaadiExam.repositories;


import com.example.SomayaAssaadiExam.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User save(User user);
    Optional<User> findByPseudo(String pseudo);

    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
