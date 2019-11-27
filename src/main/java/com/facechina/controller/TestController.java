package com.facechina.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author:
 * Date: 2019-11-19 16:03
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试")
public class TestController {


    @PostMapping("/hello")
    @ApiOperation(value = "hello")
    public String sayTest() {
        return "hellohello";
    }
}
