package com.facechina.controller;

import com.facechina.common.ResponseMO;
import com.facechina.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * Author:
 * Date: 2019-11-19 16:03
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试")
public class TestController extends BaseController{


    @Autowired
    private RedisService redisService;

    @PostMapping("/hello")
    @ApiOperation(value = "hello")
    public String sayTest() {
        return "hellohello";
    }

    @PostMapping("/testSet")
    @ApiOperation(value = "redis写入")
    public ResponseMO setRedis(@RequestParam String key,@RequestParam String value) {
        redisService.set(key,value);
        return success("redis插入成功");
    }


    @GetMapping("/testGet")
    @ApiOperation(value = "redis读值")
    public ResponseMO getRedis(@RequestParam String key) {
        String value = (String) redisService.get(key);
        return success(value);
    }

}
