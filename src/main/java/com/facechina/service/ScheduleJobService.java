package com.facechina.service;

import com.facechina.job.ScheduleJob;
import com.facechina.job.ScheduleJobLog;
import org.quartz.SchedulerException;

/**
 * Description:
 * Author:
 * Date: 2019-11-28 17:18
 */
public interface ScheduleJobService {


    //保存任务日志
    void save(ScheduleJobLog scheduleJobLog);

    //保存任务
    void save(ScheduleJob scheduleJob) throws SchedulerException;

    //执行任务
    void executeJob(Long jobId) throws SchedulerException;
}
