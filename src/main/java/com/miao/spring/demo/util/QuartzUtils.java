package com.miao.spring.demo.util;

import com.miao.spring.demo.domain.JobTask;
import org.quartz.*;

public class QuartzUtils {


    /**
     * 运行一个任务
     * 1. 没有任务则新建、运行
     * 2. 任务存在则恢复运行
     * @param scheduler
     * @param jobClassPath
     * @param jobName
     * @param groupName
     * @param cronExpression
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    public static void runTask(Scheduler scheduler, String jobClassPath, String jobName, String groupName, String cronExpression) throws SchedulerException, ClassNotFoundException {
        JobKey jobKey = new JobKey(jobName, groupName);
        TriggerKey triggerKey = new TriggerKey(jobName, groupName);

        if (scheduler.getJobDetail(jobKey)==null){
            //获取到定时任务的执行类  必须是类的绝对路径名称
            //定时任务类需要是job类的具体实现 QuartzJobBean是job的抽象类。
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(jobClassPath);
            // 构建定时任务信息
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).build();
            // 设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            // 构建触发器trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        }else {
            scheduler.resumeJob(jobKey);
        }

    }



    /**
     * 暂停定时任务
     * @param scheduler
     * @param jobTask
     */
    public static void pauseScheduleJob(Scheduler scheduler, JobTask jobTask){
        JobKey jobKey = JobKey.jobKey(jobTask.getJobName(), jobTask.getJobGroup());
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            System.out.println("暂停定时任务出错："+e.getMessage());
        }
    }

    /**
     * 根据任务名称恢复定时任务
     * @param scheduler  调度器
     * @param jobName    定时任务名称
     * @throws SchedulerException
     */
    public static void resumeScheduleJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            System.out.println("启动定时任务出错："+e.getMessage());
        }
    }

    /**
     * 根据任务名称立即运行一次定时任务
     * @param scheduler     调度器
     * @param jobName       定时任务名称
     * @throws SchedulerException
     */
    public static void runOnce(Scheduler scheduler, String jobName){
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            System.out.println("运行定时任务出错："+e.getMessage());
        }
    }

    /**
     * 更新定时任务
     * @param scheduler   调度器
     * @param jobTask  定时任务信息类
     * @throws SchedulerException
     */
    public static void updateScheduleJob(Scheduler scheduler, JobTask jobTask)  {
        try {
            //获取到对应任务的触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(jobTask.getJobName());
            //设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobTask.getCronExpression());
            //重新构建任务的触发器trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置对应的job
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务出错："+e.getMessage());
        }
    }

    /**
     * 根据定时任务名称从调度器当中删除定时任务
     * @param scheduler 调度器
     * @param jobName   定时任务名称
     * @throws SchedulerException
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            System.out.println("删除定时任务出错："+e.getMessage());
        }
    }











}
