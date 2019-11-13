package com.miao.spring.demo.config.generator;

import com.miao.spring.demo.job.PrintJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QuartzConfig {

    @Autowired
    Scheduler scheduler;

    @Bean
    public JobDetail testQuartz2() {
        return JobBuilder.newJob(PrintJob.class).withIdentity("PrintJob").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger2() {
        //cron方式，每隔5秒执行一次
        return TriggerBuilder.newTrigger().forJob(testQuartz2())
                .withIdentity("PrintJob")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                .build();
    }

}
