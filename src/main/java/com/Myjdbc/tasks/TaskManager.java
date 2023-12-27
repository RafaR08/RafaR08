package com.Myjdbc.tasks;

import com.Myjdbc.Repositiry.FechasRepository;
import com.Myjdbc.entity.Fechas;
import com.Myjdbc.impl.EjecutarPorHora;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TaskManager {
    private List<Fechas> fechasCapturadas;
    @Autowired
    FechasRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(TaskManager .class);

    public TaskManager(){
        this.fechasCapturadas = new ArrayList<>();
    }

    public void addTask(Fechas fecha){
        fechasCapturadas.add(fecha);
        System.out.println("guardado en addTask"+ fechasCapturadas.size());
        fechasCapturadas.stream()
                        .forEach(d -> {
                            System.out.println("idFecha:" + d.getIdFechas() + " idCustomer" + d.getIdCustomer() + " hora:" + d.getHora());
                                    repository.actualizar(d.getIdFechas());

                        }
                        );
       // System.out.println(fechasCapturadas);
        fechasCapturadas.clear();

    }


    public void runTask() {

        LocalDateTime horaActual = LocalDateTime.now();

        logger.info("despertando runTask");

        //verificar dia habil y hora
      //  if(horaActual.getDayOfWeek() != DayOfWeek.SATURDAY && horaActual.getDayOfWeek() != DayOfWeek.SUNDAY){
           // if(horaActual.getHour() >=9 && horaActual.getHour()<20){
                fechasCapturadas.stream()
                        //.filter(h ->   horaActual.equals(h.getHora()))//todo: aplicar predicate
                        .forEach(d->{
                            logger.info("Se han actualizado registros " + horaActual +" id: "
                                    + d.getIdCustomer());
                            repository.actualizar(d.getIdFechas());

                        });

            //}


        //}
    }



}
