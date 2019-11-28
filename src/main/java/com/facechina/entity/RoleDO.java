package com.facechina.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "F_ROLE")
public class RoleDO extends BaseDO{


    @Column(name = "ROLE_ID")
    private String roleId;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Column(name = "ROLE_DESCRIPTION")
    private String roleDescription;
    @Column(name = "ROLE_CREATETIME")
    private String roleCreatetime;
    @Column(name = "PERMISSION_ID")
    private String permissionId;

}