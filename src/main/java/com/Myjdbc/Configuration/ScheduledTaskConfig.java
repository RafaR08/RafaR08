package com.Myjdbc.Configuration;



import com.Myjdbc.component.EjecutarPorHora;
import com.Myjdbc.component.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;


@Configuration
@EnableScheduling
public class ScheduledTaskConfig implements SchedulingConfigurer {


    @Autowired
    EjecutarPorHora hora;

    @Autowired
    TaskManager taskManager;



    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.setScheduler(taskScheduler());

        taskRegistrar.addCronTask(() ->hora.run(),"* * 9-20 * * MON-FRI");
        taskRegistrar.addCronTask(() -> taskManager.run(),"0 8 18 * * MON-FRI");

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
