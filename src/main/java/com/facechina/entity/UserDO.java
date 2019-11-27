package com.facechina.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserDO {

    private Integer id;

    private String userId;

    private String userName;

    private String userLoginName;

    private Integer userAge;

    private String userEmail;

    private String userPassword;

    private String userSalt;

    private Integer userState;

    private Date userCreatetime;

    private Date userLasttime;

    private Integer userLoginCount;

    private String userRoleId;

}