package com.example.gourmet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gourmet.Domain.Review;
import com.example.gourmet.Repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * レビューをDBへ登録します
     * @param comment
     * @param registerId
     * @param articleId
     */
    public void insert(String comment, Integer registerId, Integer articleId){
        reviewRepository.insert(comment, registerId, articleId);
    }

    public List<Review> list(Integer articleId){
        return reviewRepository.list(articleId);
    }
} 
