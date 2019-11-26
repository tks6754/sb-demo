package com.miao.spring.demo.job;

import com.miao.spring.demo.dao.TaskRecordMapper;
import com.miao.spring.demo.domain.TaskRecord;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;


@Component
@Slf4j
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class PrintJob extends QuartzJobBean {

    @Autowired
    TaskRecordMapper taskRecordMapper;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setStartTime(new Date());

        System.out.println("print -- print -- print --");

        taskRecord.setEndTime(new Date());
        taskRecord.setJobClass(context.getJobDetail().getJobClass().getName());
        taskRecord.setJobName(context.getJobDetail().getKey().getName());
        taskRecord.setJobGroup(context.getJobDetail().getKey().getGroup());
        taskRecord.setTaskResult(1);
        taskRecord.setTaskMessage("success");
        taskRecordMapper.saveTaskRecord(taskRecord);
    }



}
