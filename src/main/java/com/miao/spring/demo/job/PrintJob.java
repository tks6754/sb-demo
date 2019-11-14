package com.miao.spring.demo.job;

import com.miao.spring.demo.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class PrintJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("print -- print -- print --");

    }



}
