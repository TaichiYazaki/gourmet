package com.example.gourmet.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Form.ArticleForm;

@Repository
public class MainpageRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);

     /**
     * 店舗の検索
     * @param articleForm
     * @return
     */
    public List<Article> researchArticle(String store) {
        String sql = "SELECT id, store, area, station, category, budget, smoke, phrase, register_id, register_nickname, created_at, img_file"
        +" FROM article WHERE store LIKE :store ORDER BY created_at DESC";
        SqlParameterSource param = new MapSqlParameterSource().addValue("store", "%"+store+"%");
        List<Article> list = template.query(sql, param, ARTICLE_ROW_MAPPER);
        return list;
    }
}
