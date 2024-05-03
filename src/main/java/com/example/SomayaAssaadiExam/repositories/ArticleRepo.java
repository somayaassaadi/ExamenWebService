package com.example.SomayaAssaadiExam.repositories;

import com.example.SomayaAssaadiExam.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {

    Article save (Article article);
    Optional<Article> findById(Long id);
    List<Article> findAll();
}
