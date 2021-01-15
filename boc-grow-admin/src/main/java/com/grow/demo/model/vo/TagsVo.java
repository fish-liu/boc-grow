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
public class TagsVo {

    /**
     * 主键
     */
    @ApiModelProperty(value = "分类id",dataType = "Integer")
    private Integer tagId;

    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名",dataType = "String")
    private String tagName;

    /**
     * 类型，（1=公共标签，2=私有标签）
     */
    @ApiModelProperty(value = "类型",dataType = "int")
    private int type;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",dataType = "Integer")
    private Integer uid;


}
