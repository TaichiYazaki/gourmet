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
public class MyPostRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public static RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);

    /**
     * 自分の投稿を表示
     * @param registerId
     * @return
     */
    public List<Article> load(Integer registerId) {
        String sql = "SELECT id, store, area, station, category, budget, smoke, phrase, register_id, register_nickname, img_file FROM article WHERE register_id=:register_id"
                        +" ORDER BY created_at DESC";
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("register_id", registerId);
        List<Article> list = template.query(sql, param, ARTICLE_ROW_MAPPER);
        return list;
    }

    /**
     * 投稿を削除します
     * @param id
     */
    public void delete (Integer id) {
        String sql = "DELETE FROM article WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }

}
