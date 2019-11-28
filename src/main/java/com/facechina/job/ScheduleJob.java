package com.facechina.job;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long jobId;

    private String beanName; //执行的类名

    private String methodName; //方法名

    private String params; //参数

    private String cronExpression; //cron表达式

    private String status; //任务状态 0，运行 1，暂停

    private String remark; //备注

    private Date createTime; //创建时间
}



















