package com.facechina.controller;

import com.facechina.common.ResponseMO;
import com.facechina.job.ScheduleJob;
import com.facechina.service.ScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Description:定时任务中心
 * Author:kbq
 * Date: 2019-11-28 10:06
 */
@Api(tags = "任务中心")
@RestController
@RequestMapping("/job")
@Slf4j
public class FaceChinaController extends BaseController {


    @Autowired
    private ScheduleJobService scheduleJobService;

    @ApiOperation(value = "新增任务")
    @PostMapping("/add")
    ResponseMO addJob(@RequestBody @Validated ScheduleJob scheduleJob) throws SchedulerException {
        String result = scheduleJobService.addJob(scheduleJob);
        return success(result);
    }


    @ApiOperation(value = "执行任务")
    @PostMapping("/execute")
    ResponseMO executeJob(@RequestParam String jobId) throws SchedulerException {
        scheduleJobService.executeJob(jobId);
        return success("任务成功执行");
    }


    /**
     * 定时任务信息
     */
    @GetMapping("/info/{jobId}")
    @ApiOperation(value = "查询定时任务")
    public ResponseMO queryJob(@PathVariable("jobId") Long jobId) {
        ScheduleJob scheduleJob = scheduleJobService.queryJob(jobId);
        return success(scheduleJob);
    }


    @PutMapping("/update")
    @ApiOperation(value = "修改定时任务")
    public ResponseMO updateJob(@Validated @RequestBody ScheduleJob scheduleJob) throws SchedulerException {
        scheduleJobService.updateJob(scheduleJob);
        return success("定时任务修改成功");
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "批量删除任务")
    public ResponseMO deleteJob(@RequestParam String jobId) throws SchedulerException {
        scheduleJobService.deleteJob(jobId);
        return success("删除任务成功");
    }

    /**
     * 暂停定时任务
     */
    @DeleteMapping("/pause")
    @ApiOperation(value = "暂停任务")
    public ResponseMO pauseJob(@RequestParam String jobId) throws SchedulerException {
        scheduleJobService.pauseJob(jobId);
        return success("暂停任务成功");
    }


    /**
     * 恢复定时任务
     */
    @DeleteMapping("/recover")
    @ApiOperation(value = "恢复任务")
    public ResponseMO recoverJob(@RequestParam String jobId) throws SchedulerException {
        scheduleJobService.recoverJob(jobId);
        return success("恢复任务成功");
    }




    }
