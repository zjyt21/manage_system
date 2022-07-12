package com.hlp.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author hlp
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@ToString
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    @Alias("编号")//hutool工具类的别名注解，可以在excel导入导出中省去中英文转换的繁琐
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @Alias("用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    @Alias("密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    @Alias("昵称")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    @Alias("邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    @Alias("电话")
    private String phone;

    @ApiModelProperty(value = "地址")
    @Alias("地址")
    private String address;

    @ApiModelProperty(value = "创建时间")
    @Alias("创建时间")
    private Date createTime;

    @ApiModelProperty(value = "头像")
    @Alias("头像")
    @TableField("avatar_url")
    private String avatar;

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
}