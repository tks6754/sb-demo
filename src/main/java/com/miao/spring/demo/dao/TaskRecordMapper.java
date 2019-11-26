package com.miao.spring.demo.dao;

import com.miao.spring.demo.domain.TaskRecord;

public interface TaskRecordMapper {

    long saveTaskRecord(TaskRecord taskRecord);

}