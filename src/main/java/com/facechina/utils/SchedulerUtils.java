package com.facechina.utils;

import com.alibaba.fastjson.JSON;
import com.facechina.job.ScheduleJob;
import com.facechina.model.QuartzJob;
import org.quartz.*;

public class SchedulerUtils {

    /**
     * 创建任务
     */
    public static void createJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
            String jobId = scheduleJob.getJobId();
            //创建Job对象
            JobDetail job = JobBuilder.newJob(QuartzJob.class).withIdentity("JOB_" + jobId).build();
            //获取cron表达式 并创建对象
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();
            //创建触发器
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("TRIGGET_" + jobId)
                    .withSchedule(cronScheduleBuilder) //将cron表达式配置到触发器
                    .build();

            //将对象josn序列化存储到Job的getJobDataMap()方法中，为后续根据获取属性执行对应的类的任务
            job.getJobDataMap().put("JOB_PARAM_KEY", JSON.toJSONString(scheduleJob));
            //存数据
            scheduler.scheduleJob(job, trigger);
            scheduler.pauseJob(JobKey.jobKey("JOB_" + jobId));//使任务处于等待状态,创建后不会执行
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新任务
     */
    public static void updateJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        //获取新的cron表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                .withMisfireHandlingInstructionDoNothing();

        String jobId = scheduleJob.getJobId();

        try {
            //拿到原有的trigger
            TriggerKey triggerKey = TriggerKey.triggerKey("TRIGGER_" + jobId);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //为原有的trigger赋予新的cron表达式
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(cronScheduleBuilder).build();
            //执行原有的trigger更新
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除任务
     */
    public static void deleteJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.deleteJob(JobKey.jobKey("JOB_" + jobId));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复任务
     */
    public static void recoverJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.resumeJob(JobKey.jobKey("JOB_" + jobId));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 立即执行定时任务
     */
    public static void execute(Scheduler scheduler, String jobId) {
        try {
            //只执行一次并且不会改变任务的状态
            scheduler.triggerJob(JobKey.jobKey("JOB_" + jobId));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停任务
     *
     * @param scheduler
     * @param jobId
     */
    public static void pauseJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.pauseJob(JobKey.jobKey("JOB_" + jobId));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
















































































































