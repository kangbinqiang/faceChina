package com.facechina.controller;

import com.facechina.common.ResponseMO;
import com.facechina.manage.QuartzJobManage;
import com.facechina.model.JobMO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/quartz")
@Api(tags = "任务控制中心")
@Slf4j
public class QuartzJobController extends BaseController {

    @Autowired
    private QuartzJobManage quartzJobManage;

    @PostMapping("/add")
    @ApiOperation(value = "新建任务")
    public ResponseMO addJob(@Validated @RequestBody JobMO jobMO) throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        quartzJobManage.createJob(scheduler, jobMO);
        return success("任务创建成功");
    }


//    /**
//     * 开始执行某个任务
//     */
//    @PostMapping("/startOne")
//    @ApiOperation(value = "开始执行某个任务")
//    public void startQuartzOneJob() {
//        try {
//            quartzJobManage.startOneJob();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 开始执行所有任务
     */
    @PostMapping("/startAll")
    @ApiOperation(value = "开始所有的任务")
    public ResponseMO startQuartzAllJob(@RequestParam String name, @RequestParam String group) {
        try {
            quartzJobManage.startJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return success("所有的任务开始执行");
    }

    /**
     * 获取Job信息
     *
     * @param name
     * @param group
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "获取任务信息")
    public ResponseMO getQuartzJob(@RequestParam String name, @RequestParam String group) {
        String info = null;
        try {
            info = quartzJobManage.getJobInfo(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return success(info);
    }

    /**
     * 修改某个任务的执行时间
     *
     * @param name
     * @param group
     * @param time
     * @return
     */
    @PutMapping("/modify")
    @ApiOperation(value = "修改某个任务的执行时间")
    public ResponseMO modifyQuartzJob(@RequestParam String name, @RequestParam String group, @RequestParam String time) {
        boolean flag = true;
        try {
            flag = quartzJobManage.modifyJob(name, group, time);
            if (flag) {
                return success("修改成功");
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return error("修改失败");
    }

    /**
     * 暂停某个任务
     *
     * @param name
     * @param group
     */
    @PutMapping(value = "/pause")
    @ApiOperation(value = "暂停某个任务")
    public ResponseMO pauseQuartzJob(@RequestParam String name, @RequestParam String group) {
        try {
            quartzJobManage.pauseJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return success("暂停成功");
    }

    /**
     * 恢复某个任务
     *
     * @param name
     * @param group
     */
    @PutMapping(value = "/resumeJob")
    @ApiOperation(value = "恢复某个任务")
    public ResponseMO resumeJob(@RequestParam String name, @RequestParam String group) {
        try {
            quartzJobManage.resumeJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return success("恢复单个任务成功");
    }

    /**
     * 暂停所有任务
     */
    @PostMapping(value = "/pauseAll")
    @ApiOperation(value = "暂停所有任务")
    public ResponseMO pauseAllQuartzJob() {
        try {
            quartzJobManage.pauseAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return success("暂停成功");
    }

    /**
     * 恢复所有任务
     */
    @PostMapping(value = "/resumeAllJob")
    @ApiOperation(value = "恢复所有任务")
    public ResponseMO resumeJob() {
        try {
            quartzJobManage.resumeAllJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return success("成功恢复所有的任务");
    }

    /**
     * 删除某个任务
     *
     * @param name
     * @param group
     */
    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "删除某个任务")
    public ResponseMO deleteJob(@RequestParam String name, @RequestParam String group) {
        try {
            quartzJobManage.deleteJob(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return success("成功删除单个任务");
    }

}