package com.example.gourmet.Domain;

import lombok.Data;

@Data
public class Review {
    private Integer id;
    private String comment;
    private Integer registerId;
    private Integer articleId;
    private Register register;
    private Article article;
}
