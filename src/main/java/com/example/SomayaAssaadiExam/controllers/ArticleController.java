package com.example.SomayaAssaadiExam.controllers;


import com.example.SomayaAssaadiExam.models.Article;
import com.example.SomayaAssaadiExam.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("products")
    public ResponseEntity<List<Article>> getAllArticle () {
        return new ResponseEntity<>(
                articleService.getAllArticles(),
                HttpStatus.OK
        );
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("products")
    public ResponseEntity<?> createCourse (@RequestBody Article article, Authentication authentication){
        return new ResponseEntity<>(
                articleService.createArticle(article),
                HttpStatus.CREATED
        );
    }

    @GetMapping("products/{id}")
    public ResponseEntity<Optional<Article>> getArticleById(@PathVariable Long id) {
        Optional<Article> article = articleService.getArticleById(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("products/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article updatedArticle = articleService.updateArticle(id, article);
        if (updatedArticle != null) {
            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("products/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        boolean deleted = articleService.deleteArticle(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
