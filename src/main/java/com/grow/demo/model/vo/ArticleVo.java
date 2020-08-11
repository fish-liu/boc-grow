package com.grow.demo.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author liuxw
 * @since 1.0
 */
@ApiModel
@Data
public class ArticleVo {

    @ApiModelProperty(value = "文章id",dataType = "Integer")
    private Integer id;

    /**
     * 文章内容
     */
    @ApiModelProperty(value="文章内容", dataType = "String")
    private String content;

    /**
     * 图片ids
     */
    @ApiModelProperty(value="图片ids", dataType = "String")
    private String imgIds;

    /**
     * 所属分类id
     */
    @ApiModelProperty(value="所属分类id", dataType = "String")
    private Integer cateId;

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id", dataType = "String")
    private Integer uid;

    /**
     * 可见度
     */
    @ApiModelProperty(value="可见度", dataType = "String")
    private Integer visibility;

    /**
     * 位置信息
     */
    @ApiModelProperty(value="位置信息", dataType = "String")
    private String location;

    /**
     * 发布时间（可以发表之前的文章）
     */
    @ApiModelProperty(value="发布时间", dataType = "String")
    private Date publishTime;

    /**
     * 用到的tag
     */
    private List<TagsVo> tagList;

}
