package com.hlp.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hlp
 * @since 2022-07-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_file")
@ApiModel(value="File对象", description="")
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "文件类型")
    private String type;

    @ApiModelProperty(value = "文件大小(KB)")
    private Long size;

    @ApiModelProperty(value = "下载链接")
    private String url;

    @ApiModelProperty(value = "是否删除")
//    @TableLogic(value = "0", delval = "1") 在yml中配置了就不用写了
    private Boolean deleted;

    @ApiModelProperty(value = "是否禁用链接")
    private Boolean enable;

    @ApiModelProperty(value = "文件md5")
    private String md5;
}
