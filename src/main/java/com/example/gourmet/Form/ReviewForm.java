package com.example.gourmet.Form;

import lombok.Data;

@Data
public class ReviewForm {
    private Integer id;
    private String comment;
    private Integer registerId;
    private Integer articleId;
}
