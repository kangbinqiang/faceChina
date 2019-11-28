package com.facechina.entity;

import lombok.Data;

import javax.persistence.Column;

/**
 * Description:
 * Author:
 * Date: 2019-11-27 17:48
 */
@Data
public class BaseDO {

    @Column(name = "ID")
    private Integer id;
}
