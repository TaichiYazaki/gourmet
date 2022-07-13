package com.example.gourmet.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.gourmet.Domain.Register;
import com.example.gourmet.Domain.Reserve;

@Repository
public class ReserveRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public static final RowMapper<Reserve> RESERVE_ROW_MAPPER = (rs, i) -> {
        Reserve reserve = new Reserve();
        reserve.setId(rs.getInt("id"));
        reserve.setReserveDate(rs.getString("reserve_date"));
        reserve.setReserverTime(rs.getInt("reserve_time"));
        reserve.setReservePeople(rs.getInt("reserve_people"));
        Register register = new Register();
        register.setId(rs.getInt("id"));
        reserve.setRegister(register);
        return reserve;
    };

    public void insert(String reserveDate, Integer reserveTime, Integer reservePeople, Integer registerId) {
        String sql = "INSERT INTO reserve (reserve_date, reserve_time, reserve_people, register_id)"
                + " VALUES(:reserveDate, :reserveTime, :reservePeople, :registerId)";
        SqlParameterSource param = new MapSqlParameterSource().addValue("reserveDate", reserveDate)
                .addValue("reserveTime", reserveTime).addValue("reservePeople", reservePeople)
                .addValue("registerId", registerId);
        template.update(sql, param);
    }
}
