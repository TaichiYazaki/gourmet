package com.example.gourmet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Repository.AreaPageRepository;

@Service
public class AreaPageService {
    
    @Autowired
    public AreaPageRepository areaPageRepository;

    /**
     * 池袋〜高田馬場・早稲田エリアの投稿を表示
     * @param area
     * @return
     */
    public List<Article> listOfIkebukuro(String area) {
       return areaPageRepository.listOfIkebukuro(area);
    }
}
