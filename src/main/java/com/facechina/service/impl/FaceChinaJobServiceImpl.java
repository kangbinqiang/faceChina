package com.facechina.service.impl;

import com.facechina.controller.mo.QuartzJobMO;
import com.facechina.model.BaseJob;
import com.facechina.service.FaceChinaJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Author:
 * Date: 2019-11-27 18:22
 */
@Service
@Slf4j
public class FaceChinaJobServiceImpl implements FaceChinaJobService {

    @Autowired
    private Scheduler scheduler;


    @Override
    public void addJob(QuartzJobMO quartzJobMO) {
        // 启动调度器
        try {
            scheduler.start();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(quartzJobMO.getJobClassName()).getClass())
                    .withIdentity(quartzJobMO.getJobClassName(),
                            quartzJobMO.getJobGroup()).build();
            //添加参数
            jobDetail.getJobDataMap().put("interfaceUrl", "测试");
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJobMO.
                    getJobCorn());

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(quartzJobMO.getJobClassName(),
                    quartzJobMO.getJobGroup())
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            log.error("创建定时任务失败" + e);
        }
    }

    /**
     * @Author: LX
     * @Description: 暂停定时任务
     * @Date: 2018/9/12 9:49
     * @Modified by:
     */
    @Override
    public void jobPause(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("暂停定时任务失败" + e);
        }
    }

    /**
     * @Author: LX
     * @Description:开始定时任务
     * @Date: 2018/9/12 9:49
     * @Modified by:
     */
    @Override
    public void jobResume(String jobClassName, String jobGroupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("开启定时任务失败" + e);
        }
    }

    @Override
    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("更新定时任务失败" + e);
        }
    }

    /**
     * @Author: LX
     * @Description: 删除定时任务
     * @Date: 2018/9/12 9:50
     * @Modified by:
     */
    @Override
    public void jobdelete(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BaseJob getClass(String classname) {
        try {
            Class<?> class1 = Class.forName(classname);
            return (BaseJob) class1.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void updateJob(QuartzJobMO quartzJobMO) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzJobMO.getJobClassName(),
                    quartzJobMO.getJobGroup());
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(
                    quartzJobMO.getJobCorn());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("更新定时任务失败" + e);
        }
    }
}
