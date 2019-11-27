package com.facechina.controller.mo;

import lombok.Data;

import java.io.Serializable;


@Data
public class QuartzJobMO implements Serializable {

    private Integer id;

    private String jobGroup;

    private String description;

    private String jobClassName;

    private String jobCorn;
}