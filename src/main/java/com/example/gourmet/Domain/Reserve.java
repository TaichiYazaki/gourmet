package com.example.gourmet.Domain;

import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;

import lombok.Data;

@Data
public class Reserve {
    
    private Integer id;
    private String reserveDate;
    private Integer reserverTime;
    private Integer reservePeople;
    private Integer registerId;
    private Register register;
}
