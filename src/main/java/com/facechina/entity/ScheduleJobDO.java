package com.facechina.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Description:
 * Author:
 * Date: 2019-11-28 17:33
 */
@Data
@Entity
@Table(name = "QRTZ_JOB_DETAILS")
public class ScheduleJobDO {

    @Column(name = "SCHED_NAME")
    private String schedName;
    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "JOB_GROUP")
    private String jobGroup;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "JOB_CLASS_NAME")
    private String jobClassName;
    @Column(name = "IS_DURABLE")
    private String isDurable;
    @Column(name = "IS_NONCONCURRENT")
    private String isNonconcurrent;
    @Column(name = "IS_UPDATE_DATA")
    private String isUpdateData;
    @Column(name = "REQUESTS_RECOVERY")
    private String requestsRecovery;
    @Column(name = "JOB_DATA")
    private byte[] jobData;

}
