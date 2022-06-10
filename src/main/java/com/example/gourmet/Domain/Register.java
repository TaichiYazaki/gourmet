package com.example.gourmet.Domain;

import lombok.Data;

@Data
public class Register {
    private Integer id;
    private String name;
    private String email;
    private String password;
}
