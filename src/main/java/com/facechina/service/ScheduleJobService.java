package com.facechina.service;

import com.facechina.job.ScheduleJob;
import com.facechina.entity.ScheduleJobLogDO;
import org.quartz.SchedulerException;

/**
 * Description:定时任务Service
 * Author:kbq
 * Date: 2019-11-28 17:18
 */
public interface ScheduleJobService {


    //保存任务日志
    void save(ScheduleJobLogDO scheduleJobLogDO);

    //保存任务
    String addJob(ScheduleJob scheduleJob) throws SchedulerException;

    //执行任务
    void executeJob(String jobId) throws SchedulerException;

    //查询定时任务
    ScheduleJob queryJob(Long jobId);

    //更新定时任务
    void updateJob(ScheduleJob scheduleJob) throws SchedulerException;

    //删除定时任务
    void deleteJob(String jobId) throws SchedulerException;

    //暂停定时任务
    void pauseJob(String jobId) throws SchedulerException;

    //恢复定时任务
    void recoverJob(String jobId) throws SchedulerException;
}
