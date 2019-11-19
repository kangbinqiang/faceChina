package com.facechina.service;

import com.facechina.entity.UserDO;

/**
 * Description:
 * Author:
 * Date: 2019-11-19 16:19
 */
public interface UserService {

    UserDO selectByPrimaryKey(Integer id);
}
