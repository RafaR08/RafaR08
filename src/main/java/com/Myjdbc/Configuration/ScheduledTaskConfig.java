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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Configuration
//@EnableAsync
@EnableScheduling
public class ScheduledTaskConfig implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskConfig.class);


    @Autowired
    EjecutarPorHora hora;

    @Autowired
    FechasRepository repository;
    @Autowired
    TaskManager taskManager;



    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());

       // taskRegistrar.addCronTask(() -> hora.run(),"* * 9-23 * * MON-FRI");

        taskRegistrar.addCronTask(() ->hora.run(),"* * 9-20 * * MON-FRI");
        taskRegistrar.addCronTask(() -> taskManager.runTask(),"0 17 12 * * MON-FRI");
    }


    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10); //tamaño pool
        // taskScheduler.setThreadNamePrefix("MyScheduler-");
        // taskScheduler.initialize();
        return taskScheduler;
    }

}
