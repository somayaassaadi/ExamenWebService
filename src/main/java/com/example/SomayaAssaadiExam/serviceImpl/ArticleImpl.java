package com.example.SomayaAssaadiExam.serviceImpl;


import com.example.SomayaAssaadiExam.models.Article;
import com.example.SomayaAssaadiExam.repositories.ArticleRepo;
import com.example.SomayaAssaadiExam.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleImpl implements ArticleService {



    @Autowired
    ArticleRepo articleRepo;
    @Override
    public Optional<Article> getArticleById(Long id) {
        return articleRepo.findById(id);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepo.save(article);
    }

    @Override
    public Article updateArticle(Long id, Article newArticle) {
        Optional<Article> optionalArticle = articleRepo.findById(id);
        if (optionalArticle.isPresent()) {
            newArticle.setId(id);
            return articleRepo.save(newArticle);
        }
        return null;
    }

    @Override
    public boolean deleteArticle(Long id) {
        Optional<Article> optionalArticle = articleRepo.findById(id);
        if (optionalArticle.isPresent()) {
            articleRepo.deleteById(id);
            return true;
        }
        return false;
    }


}
