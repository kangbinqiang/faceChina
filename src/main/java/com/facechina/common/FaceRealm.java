package com.facechina.common;

import com.facechina.entity.UserDO;
import com.facechina.service.RedisService;
import com.facechina.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

/**
 * Description:
 * Author:
 * Date: 2019-11-19 16:51
 */
public class FaceRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        //授权信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //给该用户设置角色
        authorizationInfo.setRoles(new HashSet<>(userService.getRolesByUserName(username)));
        //给用户设置权限
        authorizationInfo.setStringPermissions(new HashSet<>(userService.getPermissionByUserName(username)));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //根据token获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        UserDO user = null;
        //根据用户名先从redis中查找
        user = (UserDO) redisService.get(userName);
        if (user != null) {
            //把当前的用户放到session中
            SecurityUtils.getSubject().getSession().setAttribute("user", user);
            //传入用户名和密码进行身份认证，并返回认证信息
            AuthenticationInfo authinfo = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), ByteSource.Util.bytes(user.getSalt()),"faceRealm");
            return authinfo;
        }
        else {
            user = userService.getUserByName(userName);
            if (user != null) {
                //把当前的用户放到session中
                SecurityUtils.getSubject().getSession().setAttribute("user", user);
                //将用户存到redis中
                redisService.set(userName,user);
                //传入用户名和密码进行身份认证，并返回认证信息
                AuthenticationInfo authinfo = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), ByteSource.Util.bytes(user.getSalt()),"faceRealm");
                return authinfo;
            } else {
                return null;
            }
        }
    }
}
