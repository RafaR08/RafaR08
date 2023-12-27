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
import java.util.ArrayList;
import java.util.List;

@Component
public class EjecutarPorHora implements  Runnable {
    private static final Logger logger = LoggerFactory.getLogger(EjecutarPorHora.class);

    public List<String> registros = new ArrayList<>();

    @Autowired
    private  FechasRepository repository;

   // private final FechasRepository repository;
   // private final  TaskManager taskManager;


    //public void ejecutarTareaPeriodica(){}

   @Autowired
  private  TaskManager taskManager;



    //public EjecutarPorHora(FechasRepository repository,TaskManager taskManager){
    //    this.repository  = repository;
    //    this.taskManager = taskManager;
    //}

    @Override
    public void run() {
      // * Date fechaActual = new Date();
        // Obtener el hora actual
        // Date fechaActual = new Date(); //todo: cambiar a LocalDate
      //*  SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
    //*    String horaActual = horaFormat.format(fechaActual);
        // Obtener el día actual
     //*   SimpleDateFormat diaFormat = new SimpleDateFormat("yyyy-MM-dd");
      //*  String diaActual = diaFormat.format(fechaActual);

        LocalDateTime fechaActual =  LocalDateTime.now();
        DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter  diaFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String horaActual = fechaActual.format(horaFormat);
        String diaActual = fechaActual.format(diaFormat);


        List<Fechas> horarios = repository.obtenerHorarioPorDia(diaActual);
            horarios.stream()
                    .filter(h ->   horaActual.equals(h.getHora()))//todo: aplicar predicate
                    .forEach(d->{
                        logger.info("Tarea ejecutada para la hora " + horaActual +" id: "
                                + d.getIdCustomer());
                       // repository.actualizar(d.getIdFechas());
                        taskManager.agregarRegistro("Tarea programada para la hora: " + horaActual);

                    });


    }

    public String getRegistros() {
        return String.valueOf(new ArrayList<>(registros));
    }

    public void actualizarBaseDeDatos(int idFecha) {
        // Actualizar registros en la base de datos para el día actual
        repository.actualizar(idFecha);

        //repository.actualizarRegistros(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    }

    private void guardarRegistros() {
        // Lógica para guardar los registros en la lista (puedes adaptar esto según tus necesidades)
        registros = taskManager.obtenerRegistros();
    }

    /*
    private boolean esDiaHabil(Date date) {
        // si el día es hábil (lunes a viernes)
        int diaDeLaSemana = date.getDay();
        return diaDeLaSemana >= 1 && diaDeLaSemana <= 5;
    }

     */
}
