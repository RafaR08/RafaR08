package com.Myjdbc.tasks;

import com.Myjdbc.entity.Fechas;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TaskManager {

    private final List<Fechas> registros;

    public TaskManager(){
        this.registros = new CopyOnWriteArrayList<>();
    }

    public void  agregarRegistro(String registro){
        Fechas fechas = new Fechas(registro);
       registros.add(fechas);
        System.out.println("registro: "+registro);
    }

    public List<Fechas> obtenerRegistros(){
        List<Fechas> registrosActualizados = new ArrayList<>(registros);
        registros.clear();
        return  registrosActualizados ;
    }
}
