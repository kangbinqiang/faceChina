package com.facechina.controller.mo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class QuartzJobMO implements Serializable {

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "任务分组")
    private String jobGroup;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "任务名称")
    private String jobClassName;
    @ApiModelProperty(value = "表达式")
    private String jobCorn;
}