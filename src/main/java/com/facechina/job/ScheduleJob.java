package com.facechina.job;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("job的id")
    private String jobId;
    @ApiModelProperty("任务名称")
    private String jobName;
    @ApiModelProperty("任务分组")
    private String jobGroup;
    @ApiModelProperty("任务描述")
    private String description;
    @ApiModelProperty("执行时间")
    private String triggerName;
    @ApiModelProperty("任务状态 0，运行 1，暂停")
    private String triggerState;
    @ApiModelProperty("执行类")
    private String jobClassName;
    @ApiModelProperty("方法名")
    private String methodName;
    @ApiModelProperty("参数")
    private String params;
    @ApiModelProperty("cron表达式")
    private String cronExpression;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("创建时间")
    private Date createTime;

}



















