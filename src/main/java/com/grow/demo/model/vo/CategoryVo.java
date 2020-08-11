package com.grow.demo.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liuxw
 * @since 1.0
 */
@ApiModel
@Data
public class CategoryVo {

    /**
     * 主键
     */
    @ApiModelProperty(value = "分类id",dataType = "Integer")
    private Integer id;

    /**
     * 分类名
     */
    @ApiModelProperty(value="分类名", dataType = "String")
    private String cateName;

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id", dataType = "Integer")
    private Integer uid;

    /**
     * 状态，0=正常，1=禁用，2=删除
     */
    @ApiModelProperty(value="状态", dataType = "int")
    private int status;

    /**
     * 类型
     */
    @ApiModelProperty(value="类型", dataType = "int")
    private int type;
}
