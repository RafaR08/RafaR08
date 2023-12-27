package com.Myjdbc.Repositiry;

import com.Myjdbc.entity.Fechas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;


@Component
public class FechasRepository {

    @Autowired
    @Qualifier("PersonaIne")
    private JdbcTemplate jdbcTemplate;

    public List<Fechas> obtenerHorarioPorDia(String dia){
        String sql = "SELECT * FROM FECHAS WHERE Dia = ? ";
        return  jdbcTemplate.query(sql,new Object[]{dia},new FechasRowMapper());
    }

    public void actualizar(int idFechas) {

        String sql = "UPDATE FECHAS SET STATUS = 'ACTUALIZADO' WHERE  IDFECHAS = ? ";
        //jdbcTemplate.update(sql,idCostumer,hora,dia);
        jdbcTemplate.update(sql,idFechas);
        System.out.println("actualizacion correcta " + "idFecha: " + idFechas);
    }




}