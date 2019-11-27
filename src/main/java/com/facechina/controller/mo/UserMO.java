package com.facechina.controller.mo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserMO {

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String userPassword;

}