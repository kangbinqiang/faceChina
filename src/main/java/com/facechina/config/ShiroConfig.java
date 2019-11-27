package com.facechina.config;

import com.facechina.common.FaceRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:Shiro配置
 * Author:kbq
 * Date: 2019-11-19 16:45
 */
@Configuration
@Slf4j
public class ShiroConfig {


    @Bean
    public FaceRealm faceRealm() {
        FaceRealm faceRealm = new FaceRealm();
        faceRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        log.info("faceRealm注册完毕");
        return faceRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        //将自定义的realm加进来
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(faceRealm());
        log.info("securityManager注册成功");
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        //定义ShiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置自定义的securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置默认登录的url
        shiroFilterFactoryBean.setLoginUrl("/page/login");
        //设置登录成功的url
        shiroFilterFactoryBean.setSuccessUrl("/page/index");
        //设置未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/page/unauthorized");
        //进行顺序拦截器设置
        Map<String, String> filtechainMap = new LinkedHashMap();
        //设置一些静态资源不拦截
        filtechainMap.put("/static/**", "anon");
        //登录页面不拦截
        filtechainMap.put("/page/loginInfo", "anon");
        filtechainMap.put("/page/login", "anon");
        filtechainMap.put("/user/add", "anon");
//        // 以“/user/admin” 开头的用户需要身份认证，authc 表示要进行身份认证
//        filtechainMap.put("/user/admin*", "authc");
//        // “/user/student” 开头的用户需要角色认证，是“admin”才允许
//        filtechainMap.put("/user/user*/**", "roles[admin]");
//        // “/user/teacher” 开头的用户需要权限认证，是“user:create”才允许
//        filtechainMap.put("/user/bigUser*/**", "perms[\"user:create\"]");
        // 配置 logout 过滤器
        filtechainMap.put("/logout", "logout");
        filtechainMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filtechainMap);
        log.info("shiroFilterFactoryBean注册成功");
        return shiroFilterFactoryBean;
    }


    /**
     * 原因：第一次启动程序shiro使用A密钥加密了cookie，第二次启动程序shiro重新生成了密钥B，当用户访问页面时，shiro会用密钥B去解密上一次用密钥A加密的cookie，
     * 导致解密失败，导致报错，所以这不影响用户登录操作(rememberMe失效罢了)，所以这种异常只会在程序重启(shiro清除session)第一次打开页面的时候出现
     * 解决办法：既然每次重启都会重新生成一对密钥，那我们就手动设置一个加解密密钥
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200000);
        cookieRememberMeManager.setCookie(simpleCookie);
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
        return cookieRememberMeManager;
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");  //MD5散列加密
        hashedCredentialsMatcher.setHashIterations(2);   //散列一次
        return hashedCredentialsMatcher;
    }


}
