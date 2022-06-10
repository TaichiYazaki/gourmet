package com.example.gourmet.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.gourmet.Domain.Register;





@Repository
public class RegisterRepository {
    
    /**
     * DBへ接続するための設定
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Register> REGISTER_ROW_MAPPER = new BeanPropertyRowMapper<>(Register.class);
    
    /**
     * ユーザー情報の登録
     */
    public void insert(Register register){
        String sql="INSERT INTO register(name, email, password) VALUES(:name, :email, :password)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(register);
        template.update(sql, param);
        
    }
    
    public Register findByMailAddress(String email) {
		String findAccountSql = "SELECT * FROM register WHERE email=:email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<Register> registerList = template.query(findAccountSql, param, REGISTER_ROW_MAPPER);
		if (registerList.size() == 0) {
			return null;
		}
		return registerList.get(0);

	}
}
