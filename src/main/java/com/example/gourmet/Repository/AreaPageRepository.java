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

@Repository
public class AreaPageRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public static RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);

    /**
     * 池袋〜高田馬場・早稲田エリアの投稿を表示
     * @param area
     * @return
     */
    public List<Article> listOfIkebukuro(String area) {
        String sql = "SELECT id, store, area, station, category, budget, smoke, phrase, register_id, register_nickname, created_at FROM article WHERE area = :area";
        SqlParameterSource param = new MapSqlParameterSource().addValue("area", area);
        List<Article> list = template.query(sql, param, ARTICLE_ROW_MAPPER);
        return list;
    }
}
