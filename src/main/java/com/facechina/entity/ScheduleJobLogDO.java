package com.facechina.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 * Author:
 * Date: 2019-11-28 17:19
 */
@Data
public class ScheduleJobLogDO {


    private String jobId;

    private String beanName;

    private String methodName;

    private String params;

    private Date createTime;

    private Long times;

    private Byte status;

    private String error;
}
