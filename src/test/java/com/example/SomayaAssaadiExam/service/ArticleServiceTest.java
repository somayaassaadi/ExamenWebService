package com.example.SomayaAssaadiExam.service;


import com.example.SomayaAssaadiExam.models.Article;
import com.example.SomayaAssaadiExam.repositories.ArticleRepo;
import com.example.SomayaAssaadiExam.serviceImpl.ArticleImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @Mock
    ArticleRepo articleRepo;

    @InjectMocks
    ArticleImpl articleImplem;

    @Test
    public void testCreateCourse(){
        Long articleId = 1L;
        String name = "article";
        String description = "description article";
        int price= 1;
        int quantity= 1;
        Article mockAticle = new Article(articleId,name,description,price,quantity);
        when(articleRepo.save(any(Article.class))).thenReturn(mockAticle);
        Article createArticle = articleImplem.createArticle(
                new Article(null,name,description,price,quantity)
        );

        assertEquals(mockAticle, createArticle);
    }


}
