package com.miao.spring.demo.config;


import com.miao.spring.demo.job.PrintJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

//    @Bean
//    public JobDetail printJobDetail(){
//        return JobBuilder.newJob(PrintJob.class)
//                .withIdentity("print-job", "print-group")
//                .storeDurably()
//                .build();
//    }

//    @Bean
//    public Trigger printJobTrigger(){
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
//
//        return TriggerBuilder.newTrigger()
//                .forJob(printJobDetail())
//                .withIdentity("print-trigger")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }



}
