package com.example.gourmet.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Domain.Register;
import com.example.gourmet.Domain.Review;

@Repository
public class ReviewRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static RowMapper<Review> REVIEW_ROW_MAPPER = (rs, i) -> {
        Review review = new Review();
        review.setId(rs.getInt("id"));
        review.setComment(rs.getString("comment"));
        review.setRegisterId(rs.getInt("register_id"));
        review.setArticleId(rs.getInt("article_id"));
        Register register = new Register();
        register.setId(rs.getInt("id"));
        register.setNickname(rs.getString("nickname"));
        Article article = new Article();
        article.setId(rs.getInt("id"));
        review.setArticle(article);
        review.setRegister(register);
        return review;
    };

   /**
    * レビューをDBへ登録します
    * @param comment
    * @param registerId
    * @param articleId
    */
    public void insert(String comment, Integer registerId, Integer articleId){
        String sql = "INSERT INTO review (comment, register_id, article_id) VALUES(:comment, :registerId, :articleId)";
        SqlParameterSource param = new MapSqlParameterSource().addValue("comment", comment).addValue("registerId", registerId).addValue("articleId", articleId);
        template.update(sql,param);
    }

    /**
     * レビューの表示
     * @param articleId
     * @return
     */
    public List<Review> list(Integer articleId){
        String sql = "SELECT rv.id, rv.comment, rv.register_id, rv.article_id, rg.id, rg.nickname, a.id"
        +" FROM review as rv"
        +" JOIN register as rg"
        +" ON rv.register_id = rg.id"
        +" JOIN article as a"
        +" ON rv.article_id = a.id"
       + " WHERE rv.article_id=:articleId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        List<Review> list = template.query(sql, param, REVIEW_ROW_MAPPER);
        return list;
    }

}
