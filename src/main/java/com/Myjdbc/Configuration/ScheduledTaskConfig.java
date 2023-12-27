package com.Myjdbc.Configuration;



import com.Myjdbc.Repositiry.FechasRepository;
import com.Myjdbc.entity.Fechas;
import com.Myjdbc.impl.EjecutarPorDia;
import com.Myjdbc.impl.EjecutarPorHora;
import com.Myjdbc.tasks.TaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Configuration
//@EnableAsync
@EnableScheduling
public class ScheduledTaskConfig implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskConfig.class);
   // @Autowired
    //FechasRepository repository;

    @Autowired
    EjecutarPorHora hora;
    @Autowired
    EjecutarPorDia dia;


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

      taskRegistrar.setTaskScheduler(taskScheduler());

      TaskManager taskManager = new TaskManager();

    //  taskRegistrar.addCronTask(this::porHora, "* * 8-15 * * MON-FRI");
       taskRegistrar.addCronTask(hora, "* * 8-15 * * MON-FRI");
        taskRegistrar.addCronTask(() ->verificarHoraTareaPeriodica(hora,taskManager), "0 31 12 * * MON-FRI");


        // Programar tarea para ejecutar la lógica de HorarioService
        taskRegistrar.addCronTask(() -> {
            hora.run();

        }, "* * 8-15 * * MON-FRI");

        // Programar tarea para verificar la hora entre las 9:00 AM y las 2:00 PM
        taskRegistrar.addCronTask(() ->{
                actualizarBaseDeDatos(hora, taskManager);
                verificarHoraTareaPeriodica(hora, taskManager);}, "* 26 16 * * MON-FRI");



    }

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10); //tamaño pool
       // taskScheduler.setThreadNamePrefix("MyScheduler-");
       // taskScheduler.initialize();
        return taskScheduler;
    }

    //@Bean
    //public TaskManager taskManager(){
      //  return new TaskManager();
    //}

    private void verificarHoraTareaPeriodica(EjecutarPorHora hora, TaskManager taskManager) {
        // Lógica de la tarea a ejecutar en momentos específicos
        hora.run();
        // Agregar registros al TaskManager
        taskManager.agregarRegistro(hora.getRegistros());
    }



    private void actualizarBaseDeDatos(EjecutarPorHora hora, TaskManager taskManager) {
        int id = 1;
        List<Fechas> registrosActualizados = hora.actualizarBaseDeDatos(id);

        // Imprimir la lista de registros actualizados
        System.out.println("Registros actualizados en la base de datos:");
        registrosActualizados.forEach(registro -> System.out.println(registro.getIdCustomer()+"dia: "+registro.getDia()));

        // Agregar registros al TaskManager
        taskManager.agregarRegistro(hora.getRegistros());
    }



/*
    public void ejecutarTareaDiarias() {

            // Obtener el día actual
        Date fechaActual = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String diaActual = dateFormat.format(fechaActual);
            // Obtener horarios para el día actual desde la base de datos

            List<Fechas> horarios = repository.obtenerHorarioPorDia(diaActual);;
            if(esDiaHabil(fechaActual) && !horarios.isEmpty() )
                horarios.stream()
                        .forEach(d->{
                            logger.info("Tarea programada para el: " + "dia " + diaActual + " hora: "+ d.getHora() +" id: "
                            + d.getIdCustomer());
                        });
            }
    }



    public void ejecutarTareaPeriodica(){

        Date fechaActual = new Date();
            // Obtener el hora actual
           // Date fechaActual = new Date(); //todo: cambiar a LocalDate
            SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
            String horaActual = horaFormat.format(fechaActual);

            // Obtener el día actual
            SimpleDateFormat diaFormat = new SimpleDateFormat("yyyy-MM-dd");
            String diaActual = diaFormat.format(fechaActual);
            List<Fechas> horarios = repository.obtenerHorarioPorDia(diaActual);
                    //obtenerHorarioPorDia(diaActual);

            horarios.stream()
                    .filter(h ->   horaActual.equals(h.getHora()))//todo: aplicar predicate
                            .forEach(d->{
                                logger.info("Tarea ejecutada para la hora " + horaActual);
                        repository.actualizar(d.getIdFechas());

                    });

    }

    private boolean esDiaHabil(Date date) {
        // si el día es hábil (lunes a viernes)
        int diaDeLaSemana = date.getDay();
        return diaDeLaSemana >= 1 && diaDeLaSemana <= 5;
    }

 */


}
