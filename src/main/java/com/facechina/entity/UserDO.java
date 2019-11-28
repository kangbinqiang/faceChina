package com.facechina.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "F_USER")
public class UserDO extends BaseDO {


    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "USER_LOGIN_NAME")
    private String userLoginName;
    @Column(name = "USER_AGE")
    private String userAge;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Column(name = "USER_SALT")
    private String userSalt;
    @Column(name = "USER_STATE")
    private String userState;
    @Column(name = "USER_CREATETIME")
    private String userCreatetime;
    @Column(name = "USER_LASTTIME")
    private String userLasttime;
    @Column(name = "USER_LOGIN_COUNT")
    private Integer userLoginCount;
    @Column(name = "USER_ROLE_ID")
    private String userRoleId;
}