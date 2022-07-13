package com.example.gourmet.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.gourmet.Domain.Register;

@SpringBootTest
public class RegisterRepositoryTest {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private RegisterRepository registerRepository;

    @BeforeEach
    void テスト用のデータ生成() {
        System.out.println("ユーザー情報の挿入開始");
        Register register = new Register();
        register.setName("test");
        register.setEmail("test@gmail.com");
        register.setPassword("test");
        registerRepository.insert(register);
        System.out.println("ユーザー情報の挿入終了");
    }

    @AfterEach
    void テスト用のデータ削除() {
        String sql = "DELETE FROM register WHERE name=:name";
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("name", "test");
        template.update(sql, param);
        System.out.println("データの削除終了");
    }

    @Test
    public void メールアドレスでユーザー情報を検索するテスト() {
        Register register = registerRepository.findByMailAddress("test@gmail.com");
        assertEquals("test@gmail.com", register.getEmail(), "メールアドレスが登録されてません。");
    }

}
