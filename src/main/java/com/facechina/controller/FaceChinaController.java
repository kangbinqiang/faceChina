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
 * Description:
 * Author:
 * Date: 2019-11-28 10:06
 */
@Api(tags = "任务中心")
@RestController
@RequestMapping("/job")
@Slf4j
public class FaceChinaController extends BaseController{


    @Autowired
    private ScheduleJobService scheduleJobService;

    @ApiOperation(value = "新增任务")
    @PostMapping("/addJob")
//    @RequiresRoles("admin")
//    @RequiresPermissions("delete")
    ResponseMO addJob(@RequestBody @Validated ScheduleJob scheduleJob) throws SchedulerException {
        scheduleJobService.save(scheduleJob);
        return success("任务成功创建");
    }


    @ApiOperation(value = "执行任务")
    @PostMapping("/executeJob")
    ResponseMO executeJob(@RequestParam Long jobId) throws SchedulerException {
        scheduleJobService.executeJob(jobId);
        return success("任务成功执行");
    }



    @ApiOperation(value = "暂停任务")
    @PostMapping("/pauseJob")
    ResponseMO pauseJob(){
        return null;
    }

    @ApiOperation(value = "开始任务")
    @PostMapping("/startJob")
    ResponseMO startJob(){
        return null;
    }


    @ApiOperation(value = "删除任务")
    @PostMapping("/deleateJob")
    ResponseMO deleteJob(){
        return null;
    }

    @ApiOperation(value = "更新任务")
    @PostMapping("/updateJob")
    ResponseMO updateJob(){
        return null;
    }



}
