package com.facechina.model;

import com.alibaba.fastjson.JSON;
import com.facechina.entity.ScheduleJobLogDO;
import com.facechina.job.ScheduleJob;
import com.facechina.service.ScheduleJobService;
import com.facechina.service.impl.ScheduleJobServiceImpl;
import com.facechina.utils.SpringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Date;


/**
 * 自定义任务
 */
public class QuartzJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("执行quartz任务。。。。。");

        String json = context.getMergedJobDataMap().getString("JOB_PARAM_KEY");
        //将获取的对象序列化的json 转化为实体类对象
        ScheduleJob scheduleJob = JSON.parseObject(json, ScheduleJob.class);

        String jobId = scheduleJob.getJobId();
        String beanName = scheduleJob.getJobClassName();
        String methodName = scheduleJob.getMethodName();
        String params = scheduleJob.getParams();

        //quartz没有被spring管理 所以通过其它方式获取service
        ScheduleJobService scheduleJobLogService = SpringUtils.getBean(ScheduleJobServiceImpl.class);
        //保存任务记录日志
        ScheduleJobLogDO scheduleJobLogDO = new ScheduleJobLogDO();
        scheduleJobLogDO.setJobId(jobId);
        scheduleJobLogDO.setBeanName(beanName);
        scheduleJobLogDO.setMethodName(methodName);
        scheduleJobLogDO.setParams(params);
        scheduleJobLogDO.setCreateTime(new Date());

        long startTime = System.currentTimeMillis();

        try {
            Object targetClass = SpringUtils.getBean(Class.forName(beanName));
            Method method = null;
            //通过反射获取方法
            if (StringUtils.isEmpty(params)) {
                method = targetClass.getClass().getDeclaredMethod(methodName, String.class);
            } else {
                method = targetClass.getClass().getDeclaredMethod(methodName);
            }

            ReflectionUtils.makeAccessible(method);//使方法具有public权限
            //根据反射执行方法
            if (StringUtils.isEmpty(params)) {
                method.invoke(targetClass, params);
            } else {
                method.invoke(targetClass);
            }

            long endTime = System.currentTimeMillis() - startTime;

            scheduleJobLogDO.setStatus((byte) 0);//保存日志里的操作状态 0:成功
            scheduleJobLogDO.setTimes(endTime);//耗时多长时间

            logger.info("任务执行成功，任务ID：" + jobId + "，总耗时：" + endTime + "毫秒");

        } catch (Exception e) {
            long endTime = System.currentTimeMillis() - startTime;
            scheduleJobLogDO.setError(e.toString().substring(2000));//错误消息
            scheduleJobLogDO.setStatus((byte) 1);//失败
            scheduleJobLogDO.setTimes(endTime);//耗时

            e.printStackTrace();
            logger.error("任务执行失败，任务ID：" + jobId);
        } finally {
            //最后调用service保存定时任务日志记录
            scheduleJobLogService.save(scheduleJobLogDO);
        }

    }

}





















































































