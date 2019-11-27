package com.facechina.mapper;

import com.facechina.entity.RoleDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper {

    List<RoleDO> getRoleByRoleId(@Param("roleId") String roleId);

}