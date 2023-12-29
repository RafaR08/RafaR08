package com.Myjdbc.rowmapper;

import com.Myjdbc.entity.Customer;
import com.Myjdbc.entity.Fechas;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FechasRowMapper implements RowMapper<Fechas> {
    @Override
    public Fechas mapRow(ResultSet rs, int rowNum) throws SQLException {

        Fechas fecha = new Fechas();

        fecha.setIdFechas(rs.getInt("idFechas"));
        fecha.setIdCustomer(rs.getInt("IDCUSTOMER"));
        fecha.setDia(rs.getDate("dia").toLocalDate());
        fecha.setHora(rs.getString("hora"));
        fecha.setStatus(rs.getString("status"));


        return fecha;
    }
}
