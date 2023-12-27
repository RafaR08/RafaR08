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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Configuration
//@EnableAsync
@EnableScheduling
public class ScheduledTaskConfig implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskConfig.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    EjecutarPorHora hora;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
        /*
        taskRegistrar.addTriggerTask(this::configureDynamicTask, triggerContext -> {
            // Configuración inicial del cron (aquí puedes usar una expresión cron estática o dinámica inicial)
            CronTrigger cronTrigger = new CronTrigger("0 0/5 * * * ?");
            return cronTrigger.nextExecutionTime(triggerContext);
        });

         */

        taskRegistrar.addCronTask(() -> hora.run(),"* * 9-23 * * MON-FRI");
    }

    /*
    private Runnable configureDynamicTask() {
        return () -> {
            // Lógica dinámica para configurar la tarea programada
            configureDynamicSchedule();
        };
    }
*/
    /*
    private void configureDynamicSchedule() {
        String sql = "SELECT Dia, Hora, UsuarioId FROM Horarios WHERE Status = 'Activo'";
        jdbcTemplate.query(sql, resultSet -> {
            Date dia = resultSet.getDate("Dia");
            String hora = resultSet.getString("Hora");
            int usuarioId = resultSet.getInt("UsuarioId");

            String cronExpression = buildCronExpression(dia, hora);
            taskRegistrar.addTriggerTask(() -> performTask(usuarioId), new CronTrigger(cronExpression));
        });
    }
*/
    private String buildCronExpression(Date dia, String hora) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String formattedDate = dateFormat.format(dia);
        return "0 " + hora + " " + formattedDate;
    }

    private void performTask(int usuarioId) {
        System.out.println("Tarea programada para el usuario con ID: " + usuarioId);
    }

    private Executor taskScheduler() {
        // Puedes personalizar el TaskScheduler según tus necesidades
        return Executors.newScheduledThreadPool(10);
    }

}
