package com.example.gourmet.Domain;

import lombok.Data;

@Data
public class Favorite {
    
    private Integer id;
    private Integer registerId;
    private Integer articleId;
    private Article article;
}
