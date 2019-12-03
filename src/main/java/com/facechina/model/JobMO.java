package com.facechina.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 * Author:
 * Date: 2019-12-03 16:27
 */
@Data
public class JobMO {

    @ApiModelProperty(value = "任务名称")
    private String jobName;
    @ApiModelProperty(value = "任务分组")
    private String jobGroup;
    @ApiModelProperty(value = "任务执行表达式")
    private String jobIcon;
    @ApiModelProperty(value = "任务类")
    private String beanName;
}
