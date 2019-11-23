package com.miao.spring.demo.util;


import com.miao.spring.demo.common.ErrorEnum;
import com.miao.spring.demo.common.exception.QuartzException;
import org.quartz.*;

public class QuartzUtils {


    /**
     * 运行一个任务
     * 1. 没有任务则新建、运行
     * 2. 任务存在则恢复运行
     * @param scheduler
     * @param jobClassPath
     * @param jobName
     * @param jobGroup
     * @param cronExpression
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    public static void runTask(Scheduler scheduler, String jobClassPath, String jobName, String jobGroup, String cronExpression) {
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            TriggerKey triggerKey = new TriggerKey(jobName, jobGroup);

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
        }catch (Exception e){
            throw new QuartzException.JobRunException(ErrorEnum.JOB_RUN_ERR.errCode(), ErrorEnum.JOB_RUN_ERR.errorMsg(), e);
        }

    }


    /**
     * 暂停定时任务
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void pauseScheduleJob(Scheduler scheduler, String jobName, String jobGroup){
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            throw new QuartzException.JobPauseException(ErrorEnum.JOB_PAUSE_ERR.errCode(), ErrorEnum.JOB_PAUSE_ERR.errorMsg(), e);
        }
    }

    /**
     * 根据任务名称立即运行一次定时任务
     * @param scheduler     调度器
     * @param jobName       定时任务名称
     * @param jobGroup       定时任务组名
     * @throws SchedulerException
     */
    public static void runOnce(Scheduler scheduler, String jobName, String jobGroup){
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            throw new QuartzException.JobRunOnceException(ErrorEnum.JOB_RUN_ONCE_ERR.errCode(), ErrorEnum.JOB_RUN_ONCE_ERR.errorMsg(), e);
        }
    }

    /**
     * 更新定时任务
     * @param scheduler   调度器
     * @param jobName   定时任务名称
     * @param jobGroup   定时任务组名
     * @param cronExpression
     * @throws SchedulerException
     */
    public static void updateScheduleJob(Scheduler scheduler, String jobName, String jobGroup, String cronExpression)  {
        try {
            //获取到对应任务的触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            //设置定时任务执行方式
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            //重新构建任务的触发器trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置对应的job
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            throw new QuartzException.JobUpdateException(ErrorEnum.JOB_UPDATE_ERR.errCode(), ErrorEnum.JOB_UPDATE_ERR.errorMsg(), e);
        }
    }

    /**
     * 根据定时任务名称从调度器当中删除定时任务
     * @param scheduler 调度器
     * @param jobName   定时任务名称
     * @param jobGroup   定时任务组名
     * @throws SchedulerException
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobName, String jobGroup) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new QuartzException.JobDeleteException(ErrorEnum.JOB_DELETE_ERR.errCode(), ErrorEnum.JOB_DELETE_ERR.errorMsg(), e);
        }
    }











}
