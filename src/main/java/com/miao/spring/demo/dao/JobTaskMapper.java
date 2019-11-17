package com.miao.spring.demo.dao;

import com.miao.spring.demo.domain.JobTask;
import com.miao.spring.demo.domain.qto.JobTaskQTO;

import java.util.List;

public interface JobTaskMapper {

    /**
     * 添加任务
     * @param jobTask
     * @return
     */
    Long saveJobTask(JobTask jobTask);

    /**
     * 删除任务
     * @param id
     * @return
     */
    Integer deleteJobTask(Long id);

    /**
     * 更新任务
     * @param jobTask
     * @return
     */
    Integer updateJobTask(JobTask jobTask);

    /**
     * 查询任务
     * @param jobTaskQTO
     * @return
     */
    List<JobTask> listJobTask(JobTaskQTO jobTaskQTO);

    /**
     * 获取任务详情
     * @param id
     * @return
     */
    JobTask getById(Long id);

}