package com.example.gourmet.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gourmet.Repository.ArticleRepository;

@SpringBootTest
public class ArticleControllerTest {
    
    @Autowired
    private ArticleRepository articleRepository;


}
