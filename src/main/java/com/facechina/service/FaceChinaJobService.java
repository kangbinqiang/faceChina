package com.facechina.service;

import com.facechina.controller.mo.QuartzJobMO;
import com.facechina.model.BaseJob;

/**
 * Description:
 * Author:
 * Date: 2019-11-27 18:21
 */
public interface FaceChinaJobService {

    void addJob(QuartzJobMO quartzJobMO);

    public void jobPause(String jobClassName, String jobGroupName);

    public void jobResume(String jobClassName, String jobGroupName);

    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression);

    public void jobdelete(String jobClassName, String jobGroupName);

    public void updateJob(QuartzJobMO quartzJobMO);

    public BaseJob getClass(String classname);

}
