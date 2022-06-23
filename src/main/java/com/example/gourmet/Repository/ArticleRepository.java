package com.example.gourmet.Repository;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.gourmet.Domain.Article;


@Repository
public class ArticleRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);

    /**
     * 全ての投稿の表示
     * @return
     */
    public List<Article> list() {
        String sql = "SELECT id, store, area, station, category, budget, smoke, phrase, register_id, register_nickname, created_at, img_file  FROM article ORDER BY created_at";
        List<Article> list = template.query(sql, ARTICLE_ROW_MAPPER);
        return list;
    } 

    /**
     * 記事の投稿
     * @param article
     * @return
     */
    public Article insert(Article article) {
        String sql = "INSERT INTO article (store, area, station, category, budget, smoke, phrase, register_id, register_nickname, img_file)" 
        +"VALUES (:store, :area, :station, :category, :budget, :smoke, :phrase, :registerId, :registerNickname, :imgFile)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);
        template.update(sql, param);
        return article;
    }
}
