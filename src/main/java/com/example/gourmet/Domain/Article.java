package com.example.gourmet.Domain;

import java.sql.Timestamp;



import lombok.Data;

@Data
public class Article {
    
    private Integer id;
    private String store;
    private String area;
    private String station;
    private String category;
    private Integer budget;
    private String smoke;
    private String phrase;
    private Integer registerId;
    private String registerNickname;
    private Timestamp createdAt;
    private String imgFile;
}
