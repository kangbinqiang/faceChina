package com.facechina.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author:
 * Date: 2019-11-19 16:32
 */
@Controller
@RequestMapping("/page")
@Api(tags = "页面跳转控制类")
public class PageController {


    @ApiOperation("登录页")
    @GetMapping("/login")
    public String goToLogin() {
        return "login";
    }
}
