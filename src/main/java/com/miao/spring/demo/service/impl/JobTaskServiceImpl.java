package com.miao.spring.demo.service.impl;

import com.miao.spring.demo.dao.JobTaskMapper;
import com.miao.spring.demo.domain.JobTask;
import com.miao.spring.demo.domain.dto.JobTaskDTO;
import com.miao.spring.demo.domain.qto.JobTaskQTO;
import com.miao.spring.demo.service.JobTaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JobTaskServiceImpl implements JobTaskService {

    @Autowired
    JobTaskMapper jobTaskMapper;


    @Override
    public Long addJobTask(JobTaskDTO jobTaskDTO) {
        JobTask jobTask = new JobTask();
        BeanUtils.copyProperties(jobTaskDTO, jobTask);

        return jobTaskMapper.saveJobTask(jobTask);
    }

    @Override
    public boolean updateJobTask(JobTaskDTO jobTaskDTO) {
        JobTask jobTask = new JobTask();
        BeanUtils.copyProperties(jobTaskDTO, jobTask);

        Integer num = jobTaskMapper.updateJobTask(jobTask);

        return num>0?true:false;
    }

    @Override
    public boolean deleteJobTask(Long id) {
        Integer num = jobTaskMapper.deleteJobTask(id);

        return num>0?true:false;
    }

    @Override
    public List<JobTaskDTO> queryJobTask(JobTaskQTO jobTaskQTO) {
        List<JobTaskDTO> jobTaskDTOList = new ArrayList<>();
        List<JobTask> jobTasks = jobTaskMapper.listJobTask(jobTaskQTO);
        BeanUtils.copyProperties(jobTasks, jobTaskDTOList);

        return jobTaskDTOList;
    }

    @Override
    public boolean runTask(Long id) {


        // 更新状态
        return false;
    }

    @Override
    public boolean stopTask(Long id) {
        return false;
    }

    @Override
    public boolean runTaskOnce(Long id) {
        return false;
    }
}
