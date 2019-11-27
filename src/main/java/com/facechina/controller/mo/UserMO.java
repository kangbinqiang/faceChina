package com.facechina.entity;

import lombok.Data;

@Data
public class UserDO {

    private Integer id;

    private String userId;

    private String userName;

    private String userPassword;

    private String roleId;

}