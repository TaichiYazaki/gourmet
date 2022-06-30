package com.example.gourmet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Repository.MyPostRepository;

@Service
@Transactional
public class MyPostService1 {
    @Autowired
    public MyPostRepository myPostRepository;

    public List<Article> load(Integer registerId){
        return myPostRepository.load(registerId);
    }

    public void delete (Integer id) {
        myPostRepository.delete(id);
    }
}
