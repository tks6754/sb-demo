package com.miao.spring.demo.controller;

import com.miao.spring.demo.domain.dto.JobTaskDTO;
import com.miao.spring.demo.domain.qto.JobTaskQTO;
import com.miao.spring.demo.http.GlobalResponse;
import com.miao.spring.demo.service.JobTaskService;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("jobTask")
public class JobTaskController {

    @Resource
    JobTaskService jobTaskService;

    @PostMapping
    public GlobalResponse addJobTask(@RequestBody JobTaskDTO jobTaskDTO){
        jobTaskService.addJobTask(jobTaskDTO);
        return GlobalResponse.success(null);
    }

    @DeleteMapping("{id}")
    public GlobalResponse deleteJobTask(@PathVariable("id") Long id){
        jobTaskService.deleteJobTask(id);
        return GlobalResponse.success(null);
    }

    @PutMapping
    public GlobalResponse updateJobTask(@RequestBody JobTaskDTO jobTaskDTO){
        jobTaskService.updateJobTask(jobTaskDTO);
        return GlobalResponse.success(null);
    }

    @PostMapping("search")
    public GlobalResponse queryJobTask(@RequestBody JobTaskQTO jobTaskQTO){
        List<JobTaskDTO> jobTaskDTOList = jobTaskService.queryJobTask(jobTaskQTO);
        return GlobalResponse.success(jobTaskDTOList);
    }

    @GetMapping("run")
    public GlobalResponse run(Long id) throws SchedulerException, ClassNotFoundException {
        jobTaskService.runTask(id);
        return GlobalResponse.success(null);
    }

    @GetMapping("stop")
    public GlobalResponse stop(Long id){
        jobTaskService.stopTask(id);
        return GlobalResponse.success(null);
    }

    @GetMapping("runOnce")
    public GlobalResponse runOnce(Long id){
        jobTaskService.runTaskOnce(id);
        return GlobalResponse.success(null);
    }

}
