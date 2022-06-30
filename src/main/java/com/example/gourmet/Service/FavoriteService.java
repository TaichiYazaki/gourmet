package com.example.gourmet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gourmet.Domain.Favorite;
import com.example.gourmet.Repository.FavoriteRepository;

@Service
@Transactional
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    /**
     * ユーザーIDに紐づくお気に入り表示
     * 
     * @param registerId
     * @return
     */
    public List<Favorite> list(Integer registerId) {
        return favoriteRepository.list(registerId);
    }

    /**
     * お気に入り追加
     * 
     * @param registerId
     * @param articleId
     */
    public void insert(Integer registerId, Integer articleId) {
        favoriteRepository.insert(registerId, articleId);
    }

    /**
     * お気に入り削除(お気に入り一覧より削除用)
     * 
     * @param id
     */
    public void delete(Integer id) {
        favoriteRepository.delete(id);
    }

    /**
     * お気に入りを1件探します(お気に入り追加用)
     * FavoriteController line44
     * @param registerId
     * @param articleId
     * @return
     */
    public List<Favorite> load(Integer registerId, Integer articleId) {
        return favoriteRepository.load(registerId, articleId);
    }
   
    /**
     * お気に入りを1件探します(お気に入り削除用)
     * FavoriteController line48
     * @param registerId
     * @param articleId
     * @return
     */
    public Favorite delete(Integer registerId, Integer articleId) {
        return favoriteRepository.delete(registerId, articleId);
    }
}
