package com.miao.spring.demo.service;

import com.miao.spring.demo.domain.dto.JobTaskDTO;
import com.miao.spring.demo.domain.qto.JobTaskQTO;

import java.util.List;

public interface JobTaskService {

    /**
     * 新建任务
     * @param jobTaskDTO
     * @return
     */
    Long addJobTask(JobTaskDTO jobTaskDTO);

    /**
     * 更新任务
     * @param jobTaskDTO
     * @return
     */
    boolean updateJobTask(JobTaskDTO jobTaskDTO);

    /**
     * 删除任务
     * @param id id
     * @return
     */
    boolean deleteJobTask(Long id);

    /**
     * 查询任务
     * @param jobTaskQTO 任务查询对象
     * @return
     */
    List<JobTaskDTO> queryJobTask(JobTaskQTO jobTaskQTO);

    /**
     * 运行任务
     * @param id
     * @return
     */
    boolean runTask(Long id);

    /**
     * 停止任务
     * @param id
     * @return
     */
    boolean stopTask(Long id);

    /**
     * 立即运行一次
     * @param id
     * @return
     */
    boolean runTaskOnce(Long id);





}
