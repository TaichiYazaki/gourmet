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
     * @param registerId
     * @return
     */
    public List<Favorite> list(Integer registerId){
       return favoriteRepository.load(registerId);
    }

    /**
     * お気に入り追加
     * @param registerId
     * @param articleId
     */
    public void insert(Integer registerId, Integer articleId) {
        favoriteRepository.insert(registerId, articleId);
    }

    /**
     * お気に入り削除
     * @param id
     */
    public void delete(Integer id) {
        favoriteRepository.delete(id);
    }
}
