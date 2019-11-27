package com.facechina.controller;

import com.facechina.common.ResponseMO;
import com.facechina.controller.mo.UserMO;
import com.facechina.entity.UserDO;
import com.facechina.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/fetchUser/{userName}")
    @ApiOperation(value = "通过id获取用户")
    public ResponseMO<UserDO> fetchUserDO(@PathVariable("userName") String userName) {
        UserDO userDO = userService.getUserByName(userName);
        return success(userDO);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增用户")
    public ResponseMO addUserDO(@Validated @RequestBody UserMO userMO) {
        userService.addUserDO(userMO);
        return success();
    }

    @PostMapping("/editPassword")
    @ApiOperation(value = "新增用户")
    public ResponseMO editPassword(@Validated @RequestBody UserMO userMO) {
        userService.editPassword(userMO);
        return success();
    }

}
