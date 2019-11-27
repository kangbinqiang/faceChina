package com.facechina.service;

import com.facechina.controller.mo.UserMO;
import com.facechina.entity.UserDO;

import java.util.List;

/**
 * Description:
 * Author:
 * Date: 2019-11-19 16:19
 */
public interface UserService {


    UserDO getUserByName(String userName);

    List<String> getRolesByUserName(String username);

    List<String> getPermissionByUserName(String username);

    void addUserDO(UserMO userMO);

    void editPassword(UserMO userMO);
}
