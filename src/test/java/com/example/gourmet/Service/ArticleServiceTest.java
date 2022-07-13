package com.example.gourmet.Service;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Repository.ArticleRepository;

@SpringBootTest
public class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;
    
    @Test
    void articleRepositoryOneOflistの呼び出し検証() {
        Integer id =1;
        articleService.oneOfList(id);
        Mockito.verify(articleRepository,times(1)).oneOfList(id);
    }

    @Test
    void articleInsertの呼び出し検証 () {
        Article article = new Article();
        articleService.insert(article);
        Mockito.verify(articleRepository, times(1)).insert(article);
    }
}
