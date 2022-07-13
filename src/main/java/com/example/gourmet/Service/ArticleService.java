package com.example.gourmet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Repository.ArticleRepository;

@Service
@Transactional
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 全ての記事を表示します
     * @return
     */
    public List<Article> list() {
        return articleRepository.list();
    }

    /**
     * 記事を1件取得します
     * レビューで利用
     * @param id
     * @return
     */
    public Article oneOfList(Integer id) {
        return articleRepository.oneOfList(id);
    }

    /**
     * 記事の投稿
     * @param article
     * @return
     */
    public Article insert(Article article) {
        return articleRepository.insert(article);
    }

}
