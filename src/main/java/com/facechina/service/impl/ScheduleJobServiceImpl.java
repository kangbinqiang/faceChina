package com.facechina.service.impl;

import com.facechina.entity.ScheduleJobDO;
import com.facechina.entity.UserDO;
import com.facechina.job.ScheduleJob;
import com.facechina.entity.ScheduleJobLogDO;
import com.facechina.mapper.ScheduleJobLogMapper;
import com.facechina.mapper.ScheduleJobMapper;
import com.facechina.service.ScheduleJobService;
import com.facechina.utils.SchedulerUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.UUID;

/**
 * Description:定时任务实现类
 * Author:kbq
 * Date: 2019-11-28 17:18
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {



    @Autowired
    private ScheduleJobMapper scheduleJobMapper;
    @Autowired
    private ScheduleJobLogMapper scheduleJobLogMapper;

    @Override
    public void save(ScheduleJobLogDO scheduleJobLogDO) {
        scheduleJobLogMapper.insertSelective(scheduleJobLogDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        String jobId = UUID.randomUUID().toString().replaceAll("-","");
        scheduleJob.setJobId(jobId);
        ScheduleJobDO scheduleJobDO = new ScheduleJobDO();
        scheduleJobDO.setSchedName("schedName");
        scheduleJobDO.setJobName(scheduleJob.getJobName());
        scheduleJobDO.setJobGroup(scheduleJob.getJobGroup());
        scheduleJobDO.setJobClassName(scheduleJob.getJobClassName());
        scheduleJobDO.setDescription(scheduleJob.getDescription());
        scheduleJobDO.setIsDurable("1");
        scheduleJobDO.setIsNonconcurrent("1");
        scheduleJobDO.setIsUpdateData("1");
        scheduleJobDO.setRequestsRecovery("1");
        scheduleJobMapper.insert(scheduleJobDO);
        SchedulerUtils.createJob(scheduler,scheduleJob);
        return jobId;
    }

    @Override
    public void executeJob(String jobId) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        SchedulerUtils.execute(scheduler,jobId);
    }

    @Override
    public ScheduleJob queryJob(Long jobId) {
       return null;
    }

    @Override
    public void updateJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        SchedulerUtils.updateJob(scheduler,scheduleJob);
    }

    @Override
    public void deleteJob(String jobId) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        SchedulerUtils.deleteJob(scheduler,jobId);
    }

    @Override
    public void pauseJob(String jobId) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        SchedulerUtils.pauseJob(scheduler,jobId);
    }

    @Override
    public void recoverJob(String jobId) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        SchedulerUtils.recoverJob(scheduler,jobId);
    }
}
