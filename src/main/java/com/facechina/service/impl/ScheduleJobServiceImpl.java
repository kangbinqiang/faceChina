package com.facechina.service.impl;

import com.facechina.entity.ScheduleJobDO;
import com.facechina.job.ScheduleJob;
import com.facechina.job.ScheduleJobLog;
import com.facechina.mapper.ScheduleJobMapper;
import com.facechina.service.ScheduleJobService;
import com.facechina.utils.SchedulerUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 * Author:
 * Date: 2019-11-28 17:18
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {



    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Override
    public void save(ScheduleJobLog scheduleJobLog) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        ScheduleJobDO scheduleJobDO = new ScheduleJobDO();
        scheduleJobDO.setSchedName("调度中心");
        scheduleJobDO.setJobName("任务名称");
        scheduleJobDO.setJobGroup("任务组");
        scheduleJobDO.setJobClassName(scheduleJob.getBeanName());
        scheduleJobDO.setIsDurable("1");
        scheduleJobDO.setIsNonconcurrent("1");
        scheduleJobDO.setIsUpdateData("1");
        scheduleJobDO.setRequestsRecovery("1");
        scheduleJobMapper.insert(scheduleJobDO);
        SchedulerUtils.createJob(scheduler,scheduleJob);
    }

    @Override
    public void executeJob(Long jobId) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        SchedulerUtils.execute(scheduler,jobId);
    }
}
