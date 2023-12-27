package com.Myjdbc.impl;

import com.Myjdbc.Repositiry.FechasRepository;
import com.Myjdbc.entity.Fechas;
import com.Myjdbc.tasks.TaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class EjecutarPorHora implements  Runnable {
    private static final Logger logger = LoggerFactory.getLogger(EjecutarPorHora.class);

    @Autowired
    private  FechasRepository repository;

   @Autowired
  private  TaskManager taskManager;


    @Override
    public void run() {
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter diaFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String horaActual = fechaActual.format(horaFormat);
        String diaActual = fechaActual.format(diaFormat);


        List<Fechas> horarios = repository.obtenerHorarioPorDia(diaActual);
            horarios.stream()
                    .filter(h ->   horaActual.equals(h.getHora()))//todo: aplicar predicate
                    .forEach(d->{
                        logger.info("Se han ingresado datos " + horaActual +" id: "
                                + d.getIdCustomer());
                        //agregar tarea al taskManager
                       // taskManager.addTask(d);

                    });



    }

}
