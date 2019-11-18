package com.miao.spring.demo.service.impl;

import com.miao.spring.demo.dao.JobTaskMapper;
import com.miao.spring.demo.domain.JobTask;
import com.miao.spring.demo.domain.dto.JobTaskDTO;
import com.miao.spring.demo.domain.qto.JobTaskQTO;
import com.miao.spring.demo.service.JobTaskService;
import com.miao.spring.demo.util.QuartzUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JobTaskServiceImpl implements JobTaskService {

    @Autowired
    JobTaskMapper jobTaskMapper;

    @Autowired
    Scheduler scheduler;

    @Override
    public Long addJobTask(JobTaskDTO jobTaskDTO) {
        JobTask jobTask = new JobTask();
        BeanUtils.copyProperties(jobTaskDTO, jobTask);
        jobTask.setJobStatus(0);

        return jobTaskMapper.saveJobTask(jobTask);
    }

    @Override
    public boolean deleteJobTask(Long id) {
        JobTask jobTask = jobTaskMapper.getById(id);
        Integer num = jobTaskMapper.deleteJobTask(id);
        QuartzUtils.deleteScheduleJob(scheduler, jobTask.getJobName(), jobTask.getJobGroup());

        return num>0?true:false;
    }

    @Override
    public boolean updateJobTask(JobTaskDTO jobTaskDTO) {
        JobTask jobTask = new JobTask();
        BeanUtils.copyProperties(jobTaskDTO, jobTask);
        Integer num = jobTaskMapper.updateJobTask(jobTask);

        JobTask job = jobTaskMapper.getById(jobTaskDTO.getId());
        QuartzUtils.updateScheduleJob(scheduler, job.getJobName(), job.getJobGroup(), job.getCronExpression());

        return num>0?true:false;
    }

    @Override
    public List<JobTaskDTO> queryJobTask(JobTaskQTO jobTaskQTO) {
        List<JobTaskDTO> jobTaskDTOList = new ArrayList<>();
        List<JobTask> jobTasks = jobTaskMapper.listJobTask(jobTaskQTO);
        if (jobTasks!=null && jobTasks.size()>0){
            for (JobTask jobTask : jobTasks) {
                JobTaskDTO jobTaskDTO = new JobTaskDTO();
                BeanUtils.copyProperties(jobTask, jobTaskDTO);
                jobTaskDTOList.add(jobTaskDTO);
            }
        }

        return jobTaskDTOList;
    }

    @Override
    public boolean runTask(Long id) throws ClassNotFoundException, SchedulerException {
        JobTask jobTask = jobTaskMapper.getById(id);
        QuartzUtils.runTask(scheduler,jobTask.getJobClass(), jobTask.getJobName(), jobTask.getJobGroup(), jobTask.getCronExpression());

        jobTask.setJobStatus(1);
        jobTaskMapper.updateJobTask(jobTask);
        return true;
    }

    @Override
    public boolean stopTask(Long id) {
        JobTask jobTask = jobTaskMapper.getById(id);
        QuartzUtils.pauseScheduleJob(scheduler, jobTask.getJobName(), jobTask.getJobGroup());
        jobTask.setJobStatus(2);
        jobTaskMapper.updateJobTask(jobTask);
        return true;
    }

    @Override
    public boolean runTaskOnce(Long id) {
        JobTask jobTask = jobTaskMapper.getById(id);
        QuartzUtils.runOnce(scheduler, jobTask.getJobName(), jobTask.getJobGroup());
        return true;
    }
}
