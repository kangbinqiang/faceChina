package com.facechina.controller;

import com.facechina.common.ResponseMO;
import com.facechina.entity.UserDO;
import com.facechina.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author:
 * Date: 2019-11-19 16:21
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
@Slf4j
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @GetMapping("/fetchUser/{id}")
    @ApiOperation(value = "通过id获取用户")
    public ResponseMO<UserDO> fetchUserDO(@PathVariable("id") Integer id) {
        UserDO userDO = userService.selectByPrimaryKey(id);
        return success(userDO);
    }
}
