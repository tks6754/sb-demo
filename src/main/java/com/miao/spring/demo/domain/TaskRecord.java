package com.miao.spring.demo.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("TaskRecord")
@Data
public class TaskRecord {
    /**
     * 
     */
    private Long id;

    /**
     * 任务名
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    private String jobClass;

    /**
     * 运行结果 1：成功 0：失败
     */
    private Integer taskResult;

    /**
     * 运行信息
     */
    private String taskMessage;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 逻辑删除 0:未删除 1:已删除
     */
    private Integer isDeleted;
}