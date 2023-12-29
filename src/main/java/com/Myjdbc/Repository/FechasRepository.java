package com.Myjdbc.Repository;

import com.Myjdbc.entity.Fechas;
import com.Myjdbc.component.TaskManager;
import com.Myjdbc.rowmapper.FechasRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class FechasRepository {

    private static final Logger logger = LoggerFactory.getLogger(TaskManager.class);

    @Autowired
    @Qualifier("PersonaIne")
    private JdbcTemplate jdbcTemplate;

    public List<Fechas> obtenerHorarioPorDia(String dia){
        String sql = "SELECT * FROM FECHAS WHERE Dia = ? ";
        return  jdbcTemplate.query(sql,new Object[]{dia},new FechasRowMapper());
    }

    public void actualizar(int idFechas) {
        String sql = "UPDATE FECHAS SET STATUS = 'ACTUALIZADO' WHERE  IDFECHAS = ? ";
        jdbcTemplate.update(sql,idFechas);
        logger.info("actualizacion correcta " + "idFecha: " + idFechas);
    }




}