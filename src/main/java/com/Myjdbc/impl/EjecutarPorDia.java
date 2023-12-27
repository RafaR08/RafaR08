package com.Myjdbc.impl;

import com.Myjdbc.Repositiry.FechasRepository;
import com.Myjdbc.entity.Fechas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Component
public class EjecutarPorDia implements  Runnable {

    private static final Logger logger = LoggerFactory.getLogger(EjecutarPorDia.class);

    @Autowired
    FechasRepository repository;

    //public void ejecutarTareaDiarias() {}



    @Override
    public void run() {

        // Obtener el día actual
        Date fechaActual = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String diaActual = dateFormat.format(fechaActual);
        // Obtener horarios para el día actual desde la base de datos

        List<Fechas> horarios = repository.obtenerHorarioPorDia(diaActual);;
        if(esDiaHabil(fechaActual) && !horarios.isEmpty() ){
            // todo: terminar la ejecucion
            horarios.stream()
                    .forEach(d->{
                        logger.info("Tarea programada para el: " + "dia " + diaActual + " hora: "+ d.getHora() +" id: "
                                + d.getIdCustomer());
                    });
        }

    }


    private boolean esDiaHabil(Date date) {
        // si el día es hábil (lunes a viernes)
        int diaDeLaSemana = date.getDay();
        return diaDeLaSemana >= 1 && diaDeLaSemana <= 5;
    }
}
