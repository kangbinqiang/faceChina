package com.facechina.controller;

import com.facechina.common.ResponseMO;
import com.facechina.controller.mo.UserMO;
import com.facechina.entity.UserDO;
import com.facechina.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * Author:
 * Date: 2019-11-19 16:32
 */
@Controller
@RequestMapping("/page")
@Api(tags = "页面跳转控制类")
@Slf4j
public class PageController extends BaseController {


    @Autowired
    private UserService userService;


    @ApiOperation("登录页")
    @GetMapping("/login")
    public String goToLogin() {
        return "login";
    }


    @ApiOperation("校验登录")
    @PostMapping("/loginInfo")
    @ResponseBody
    public ResponseMO checkLogin(@RequestBody @Validated UserMO userMO, HttpServletRequest request) {
        //根据用户名和密码创建token
        UsernamePasswordToken token = new UsernamePasswordToken(userMO.getUserName(), userMO.getUserPassword());
        token.setRememberMe(true);
        //获取Subject认证主体
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            subject.checkRole("admin");
            subject.checkPermission("add");
            UserDO userDO = userService.getUserByName(userMO.getUserName());
            request.getSession().setAttribute("user", userDO);
            if (subject.isAuthenticated()) {
                return success(userMO);
            } else {
                return error("登录失败");
            }
        } catch (Exception e) {
            log.error("认证失败" + e);
            return error("登录异常");
        }
    }

    @ApiOperation("登出页")
    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

    @ApiOperation("首页")
    @GetMapping("/index")
    public String goToIndex() {
        return "index";
    }


    @ApiOperation("错误页")
    @GetMapping("/error")
    public String goToError() {
        return "error";
    }


    @ApiOperation("未授权页")
    @GetMapping("/unauthorized")
    public String goToUnauthorized() {
        return "unauthorized";
    }
}
