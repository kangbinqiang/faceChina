package com.facechina.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "F_PERMISSION")
public class PermissionDO extends BaseDO{

    @Column(name = "PERMISSION_ID")
    private String permissionId;
    @Column(name = "PERMISSION_NAME")
    private String permissionName;
    @Column(name = "PERMISSION_DESCRIPTION")
    private String permissionDescription;
    @Column(name = "CREATE_DATE")
    private String createDate;
}