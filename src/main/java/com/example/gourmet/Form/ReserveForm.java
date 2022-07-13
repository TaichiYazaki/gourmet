package com.example.gourmet.Form;

import lombok.Data;

@Data
public class ReserveForm {
    
    private Integer id;
    private String reserveDate;
    private Integer reserveTime;
    private Integer reservePeople;
    private Integer registerId;
}
