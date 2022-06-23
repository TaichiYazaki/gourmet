package com.example.gourmet.Repository;

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
public class MypageRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static RowMapper<Register> REGISTER_ROW_MAPPER = new BeanPropertyRowMapper<>(Register.class);

    public Register loadId(Integer id) {
        String sql = "SELECT * FROM register WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Register register = template.queryForObject(sql, param, REGISTER_ROW_MAPPER);
        return register;
    }

    /**
     * プロフィール更新処理
     * 
     * @param register
     * @return
     */
    public Register update(Register register) {
        String sql = "UPDATE register SET nickname=:nickname,phrase=:phrase WHERE id=:id ";
        SqlParameterSource param = new BeanPropertySqlParameterSource(register);
        template.update(sql, param);
        return register;
    };

    public Register image(Register register, byte[] bytes) {
        //Register register = loadId(id);
        System.out.println(register);
        String sql = "UPDATE profile_img SET image=:image ";
        SqlParameterSource param = new BeanPropertySqlParameterSource(bytes);
        template.update(sql, param);
        return register;
    }

    public Register imgFileUpdate(Register register) {
        String sql = "UPDATE register SET img_file=:imgFile WHERE id=:id ";
        SqlParameterSource param = new BeanPropertySqlParameterSource(register);
        template.update(sql, param);
      return register;
    };


}
