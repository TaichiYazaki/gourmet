package com.example.gourmet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Repository.MainpageRepository;

@Service
@Transactional
public class MainpageService {

    @Autowired
    private MainpageRepository mainpageRepository;
    
    /**
     * 店舗の検索
     * @param articleForm
     * @return
     */
    public List<Article> researchArticle(String store) {
        return mainpageRepository.researchArticle(store);
    }
}
