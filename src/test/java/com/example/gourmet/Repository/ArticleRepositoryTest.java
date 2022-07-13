package com.example.gourmet.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.gourmet.Domain.Article;

@SpringBootTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private NamedParameterJdbcTemplate template;

    @BeforeEach
    void テスト用のデータ生成() {
        System.out.println("記事の挿入開始");
        Article article = new Article();
        article.setStore("testStore");
        article.setArea("testArea");
        article.setStation("testStation");
        article.setCategory("testCategory");
        article.setBudget(1);
        article.setSmoke("testSmoke");
        article.setPhrase("testPhrase");
        article.setRegisterId(10);
        article.setRegisterNickname("testRegisterNickname");
        article.setImgFile("testImgFile");
        articleRepository.insert(article);
        System.out.println("記事の挿入終了");
    }

    @AfterEach
    void テスト用のデータ削除() {
        String sql = "DELETE FROM article WHERE store=:store";
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("store", "testStore");
        template.update(sql, param);
        System.out.println("データ削除完了");
    }

    @Test
    void 全ての投稿を取得するテスト() {
        Integer countId = template.queryForObject("SELECT count(id) FROM article", new MapSqlParameterSource(),
                Integer.class);
        List<Article> list = articleRepository.list();
        assertEquals(countId, list.size(), "不一致");
    }

    @Test
    void idより記事を1件取得するテスト() {
        String sql = "SELECT max(id) from article";
        Integer maxId = template.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
        Article article = articleRepository.oneOfList(maxId);
        assertEquals("testStore", article.getStore());
    }

}
