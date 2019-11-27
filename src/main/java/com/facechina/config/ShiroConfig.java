package com.facechina.config;

import com.facechina.common.FaceRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 * Author:
 * Date: 2019-11-19 16:45
 */
@Configuration
public class ShiroConfig {


    @Bean(name = "shi")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/page/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/page/error");
        shiroFilterFactoryBean.setSuccessUrl("page/index");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/page/login", "anon");
//        filterChainDefinitionMap.put("/login", "anon");
//        filterChainDefinitionMap.put("/", "anon");
//        filterChainDefinitionMap.put("/page/**", "anon");
//        filterChainDefinitionMap.put("/api/**", "anon");

//        filterChainDefinitionMap.put("/admin/**", "authc");
//        filterChainDefinitionMap.put("/user/**", "authc");
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(faceRealm());
        return defaultSecurityManager;
    }

    @Bean
    public FaceRealm faceRealm() {
        FaceRealm faceRealm = new FaceRealm();
        return faceRealm;
    }
}
