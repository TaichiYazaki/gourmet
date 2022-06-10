package com.example.gourmet.Form;


import lombok.Data;

@Data
public class RegisterForm {
    /**名前*/
   
    private String name;
    /**メールアドレス */
   
    private String email;
    /**パスワード */
   
    private String password;
}
