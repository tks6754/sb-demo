package com.miao.spring.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class JobTask {
    /**
     * id
     */
    private Long id;

    /**
     * 任务名
     */
    private String jobName;

    /**
     * 任务描述
     */
    private String description;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    private String jobClass;

    /**
     * 任务状态 -1:删除 0:创建 1:运行 2:停止
     */
    private Integer jobStatus;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}