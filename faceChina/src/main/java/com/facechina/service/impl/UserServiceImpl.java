package com.facechina.service.impl;

import com.facechina.entity.UserDO;
import com.facechina.mapper.UserMapper;
import com.facechina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Author:
 * Date: 2019-11-19 16:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
