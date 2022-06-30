package com.example.gourmet.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Domain.Favorite;

@Repository
public class FavoriteRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public static final RowMapper<Favorite> FAVORITE_ROW_MAPPER = (rs, i) -> {
        Favorite favorite = new Favorite();
        favorite.setId(rs.getInt("id"));
        favorite.setRegisterId(rs.getInt("register_id"));
        favorite.setArticleId(rs.getInt("article_id"));
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setStore(rs.getString("store"));
        article.setArea(rs.getString("area"));
        article.setStation(rs.getString("station"));
        article.setCategory(rs.getString("category"));
        article.setBudget(rs.getInt("budget"));
        article.setSmoke(rs.getString("smoke"));
        article.setPhrase(rs.getString("phrase"));
        article.setRegisterId(rs.getInt("register_id"));
        article.setRegisterNickname(rs.getString("register_nickname"));
        article.setImgFile(rs.getString("img_file"));
        favorite.setArticle(article);
        return favorite;
    };

    /**
     * お気に入り一覧の表示
     * 
     * @param registerId
     * @return
     */
    public List<Favorite> list(Integer registerId) {
        String sql = "SELECT f.id, f.register_id, f.article_id, r.id, a.store, a.area, a.station, a.category, a.budget, a.smoke,a.phrase, a.register_id, a.register_nickname, a.img_file"
                + " FROM favorite as f"
                + " JOIN article as a"
                + " ON f.article_id = a.id"
                + " JOIN register as r"
                + " ON f.register_id = r.id"
                + " WHERE f.register_id= :register_id";
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("register_id", registerId);
        List<Favorite> list = template.query(sql, param, FAVORITE_ROW_MAPPER);
        return list;
    }

    /**
     * お気に入りを1件探します(お気に入り追加用)
     * FavoriteController line44
     * 
     * @param registerId
     * @param articleId
     * @return
     */
    public List<Favorite> load(Integer registerId, Integer articleId) {
        // String sql = "SELECT id, register_id, article_id FROM favorite WHERE
        // register_id=:register_id AND article_id=:article_id";
        String sql = "SELECT f.id, f.register_id, f.article_id, r.id, a.store, a.area, a.station, a.category, a.budget, a.smoke,a.phrase, a.register_id, a.register_nickname, a.img_file"
                + " FROM favorite as f"
                + " JOIN article as a"
                + " ON f.article_id = a.id"
                + " JOIN register as r"
                + " ON f.register_id = r.id"
                + " WHERE f.register_id= :register_id AND f.article_id= :article_id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("register_id", registerId)
                .addValue("article_id", articleId);
        List<Favorite> favorite = template.query(sql, param, FAVORITE_ROW_MAPPER);
        return favorite;
    }

    /**
     * お気に入りを1件探します(お気に入り削除用)
     * FavoriteController line48
     * 
     * @param registerId
     * @param articleId
     * @return
     */
    public Favorite delete(Integer registerId, Integer articleId) {
        String sql = "SELECT f.id, f.register_id, f.article_id, r.id, a.store, a.area, a.station, a.category, a.budget, a.smoke,a.phrase, a.register_id, a.register_nickname, a.img_file"
                + " FROM favorite as f"
                + " JOIN article as a"
                + " ON f.article_id = a.id"
                + " JOIN register as r"
                + " ON f.register_id = r.id"
                + " WHERE f.register_id= :register_id AND f.article_id= :article_id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("register_id", registerId)
                .addValue("article_id", articleId);
        Favorite favorite = template.queryForObject(sql, param, FAVORITE_ROW_MAPPER);
        return favorite;
    }

    /**
     * 投稿のお気に入り追加
     * 
     * @param registerId
     * @param articleId
     */
    public void insert(Integer registerId, Integer articleId) {
        String sql = "INSERT INTO favorite (register_id, article_id) VALUES (:registerId, :articleId)";
        SqlParameterSource param = new MapSqlParameterSource().addValue("registerId", registerId).addValue("articleId",
                articleId);
        template.update(sql, param);
    }

    /**
     * お気に入りを削除します
     * 
     * @param id
     */

    public void delete(Integer id) {
        String sql = "DELETE FROM favorite WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }
}
