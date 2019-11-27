package com.facechina.model;


import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * Description:
 * Author:
 * Date: 2019-11-27 19:07
 */
@Slf4j
public class BaseJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //执行业务逻辑
        //1、获取定时任务传递来的参数
        JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
        String interfaceUrl = map.getString("interfaceUrl");
        System.out.println("当前时间为："+ LocalDateTime.now().toString());
        try{
            Thread.sleep(3000);
        }catch (Exception e) {
            log.info("---------定时任务error--------");
        }
    }
}
