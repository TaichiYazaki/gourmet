package com.example.gourmet.Domain;

import java.sql.Timestamp;



import lombok.Data;

@Data
public class Article {
    
    private Integer id;
    private String store;
    private Integer area;
    private String station;
    private Integer category;
    private Integer budget;
    private Integer smoke;
    private String phrase;
    private Integer registerId;
    private String registerNickname;
    private Timestamp createdAt;

}
