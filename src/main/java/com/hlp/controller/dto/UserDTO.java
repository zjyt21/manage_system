package com.hlp.controller.dto;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接收前端登录请求的参数
 *
 * @author Mr.Han
 * @create 2022-07-06 21:50
 */
@Data
@ApiModel(value="用户数据", description="接收前端登录请求的参数")
public class UserDTO {
    @ApiModelProperty(value = "用户名")
    @Alias("用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    @Alias("密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    @Alias("昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    @Alias("头像")
    private String avatar;

    private String token;
}
