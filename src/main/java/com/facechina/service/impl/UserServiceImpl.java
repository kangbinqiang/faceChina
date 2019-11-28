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
import tk.mybatis.mapper.entity.Example;

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
        Example example = new Example(UserDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        return userMapper.selectOneByExample(example);
    }

    /**
     * 用账号查询角色列表
     * @param username
     * @return
     */
    @Override
    public List<String> getRolesByUserName(String username) {
        List<String> result = new ArrayList<>();
        UserDO userDO = getUserByName(username);
        if (userDO != null) {
            Example example = new Example(RoleDO.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleId", userDO.getUserRoleId());
            List<RoleDO> list = roleMapper.selectByExample(example);
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
        UserDO userDO = getUserByName(username);
        if (userDO != null) {
            //查找角色
            List<RoleDO> roleDOList = getRoleList(userDO.getUserRoleId());
            if (CollectionUtils.isEmpty(roleDOList)) {
                return result;
            }
            for (RoleDO roleDO : roleDOList) {
                //查找权限
                Example example = new Example(PermissionDO.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("permissionId", roleDO.getPermissionId());
                List<PermissionDO> permissionDOList = permissionMapper.selectByExample(example);
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


    public List<RoleDO> getRoleList(String roleId) {
        Example example = new Example(RoleDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        List<RoleDO> roleDOList = roleMapper.selectByExample(example);
        return roleDOList;
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
        userDO.setUserLoginName("login"+userMO.getUserName());
        userDO.setUserPassword(password);
        userDO.setUserSalt(salt);
        userDO.setUserRoleId("admin");
        userMapper.insertSelective(userDO);
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
            newUser.setUserRoleId(userDO.getUserRoleId());
            newUser.setUserPassword(password);
            newUser.setUserSalt(salt);
            Example example = new Example(UserDO.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId", userDO.getUserId());
            userMapper.updateByExample(newUser,example);
        }
    }
}
