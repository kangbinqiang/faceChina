package com.facechina.mapper;

import com.facechina.entity.PermissionDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PermissionMapper {

    List<PermissionDO> getPermissionByPermissionId(@Param("permissionId") String permissionId);

}