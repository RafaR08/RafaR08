package com.Myjdbc.component;

import com.Myjdbc.Repository.FechasRepository;
import com.Myjdbc.entity.Fechas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskManager implements  Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TaskManager .class);
    private List<Fechas> fechasCapturadas;
    @Autowired
    FechasRepository repository;


    public TaskManager(){
        this.fechasCapturadas = new ArrayList<>();
    }

    public void addTask(Fechas fecha){
        fechasCapturadas.add(fecha);
        logger.info("guardado en la lista "+ "No.registros: "+fechasCapturadas.size());
        fechasCapturadas.stream()
                        .forEach(d -> {
                            logger.info("idFecha:" + d.getIdFechas() + " idCustomer: " + d.getIdCustomer() + " hora:" + d.getHora());
                            repository.actualizar(d.getIdFechas());
                        });
        
    }


    @Override
    public void run() {

        logger.info("despertando runTask");
        System.out.println( fechasCapturadas.size());

        fechasCapturadas.stream()
                .forEach(d -> {
                    System.out.println("entra al ciclo");
                    System.out.println("idFecha:" + d.getIdFechas() + " idCustomer" + d.getIdCustomer() + " hora:" + d.getHora());
                     repository.actualizar(d.getIdFechas());
                });
    }


}
