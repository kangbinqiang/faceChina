package com.facechina.mapper;

import com.facechina.entity.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {


    UserDO getUserByName(@Param("userName") String userName);

    void addUser(UserDO userDO);

    void editPassword(UserDO userDO);
}