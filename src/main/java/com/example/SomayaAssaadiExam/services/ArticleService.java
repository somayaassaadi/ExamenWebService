package com.example.SomayaAssaadiExam.services;

import com.example.SomayaAssaadiExam.models.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    Optional<Article> getArticleById(Long id);

    List<Article> getAllArticles();

    Article createArticle(Article article);
    Article updateArticle(Long id, Article newArticle);

    boolean deleteArticle(Long id);


}
