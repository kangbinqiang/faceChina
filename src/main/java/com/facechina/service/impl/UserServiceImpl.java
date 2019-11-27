package com.facechina.service.impl;

import com.facechina.controller.mo.UserMO;
import com.facechina.entity.PermissionDO;
import com.facechina.entity.RoleDO;
import com.facechina.entity.UserDO;
import com.facechina.mapper.PermissionMapper;
import com.facechina.mapper.RoleMapper;
import com.facechina.mapper.UserMapper;
import com.facechina.service.UserService;
import com.facechina.utils.ShiroEncryption;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Description:
 * Author:
 * Date: 2019-11-19 16:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;


    /**
     * 用账号查询用户信息
     * @param userName
     * @return
     */
    @Override
    public UserDO getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    /**
     * 用账号查询角色列表
     * @param username
     * @return
     */
    @Override
    public List<String> getRolesByUserName(String username) {
        List<String> result = new ArrayList<>();
        UserDO userDO = userMapper.getUserByName(username);
        if (userDO != null) {
            List<RoleDO> list = roleMapper.getRoleByRoleId(userDO.getRoleId());
            if (CollectionUtils.isEmpty(list)) {
                return result;
            }
            for (RoleDO roleDO : list) {
                result.add(roleDO.getRoleName());
            }
        }
        return result;
    }

    /**
     * 用账号查询权限列表
     * @param username
     * @return
     */
    @Override
    public List<String> getPermissionByUserName(String username) {
        List<String> result = new ArrayList<>();
        UserDO userDO = userMapper.getUserByName(username);
        if (userDO != null) {
            //查找角色
            List<RoleDO> roleDOList = roleMapper.getRoleByRoleId(userDO.getRoleId());
            if (CollectionUtils.isEmpty(roleDOList)) {
                return result;
            }
            for (RoleDO roleDO : roleDOList) {
                //查找权限
                List<PermissionDO> permissionDOList = permissionMapper.getPermissionByPermissionId(roleDO.getPermissionId());
                if (CollectionUtils.isEmpty(permissionDOList)) {
                    return result;
                }
                for (PermissionDO permissionDO : permissionDOList) {
                    result.add(permissionDO.getPermissionName());
                }
            }
        }
        return result;
    }


    /**
     * 新增用户
     * @param userMO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserDO(UserMO userMO) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String password = ShiroEncryption.shiroEncryption(userMO.getUserPassword(),salt);
        UserDO userDO = new UserDO();
        userDO.setUserId(UUID.randomUUID().toString().replaceAll("-",""));
        userDO.setUserName(userMO.getUserName());
        userDO.setUserPassword(password);
        userDO.setSalt(salt);
        userDO.setRoleId("admin");
        userMapper.addUser(userDO);
    }


    /**
     * 更新密码
     * @param userMO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editPassword(UserMO userMO) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String password = ShiroEncryption.shiroEncryption(userMO.getUserPassword(),salt);
        UserDO userDO = getUserByName(userMO.getUserName());
        if (userDO != null) {
            UserDO newUser = new UserDO();
            newUser.setUserId(userDO.getUserId());
            newUser.setUserName(userDO.getUserName());
            newUser.setRoleId(userDO.getRoleId());
            newUser.setUserPassword(password);
            newUser.setSalt(salt);
            userMapper.editPassword(newUser);
        }
    }
}
