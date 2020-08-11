package com.grow.demo.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuxw
 * @since 1.0
 */
@ApiModel("发表文章对象")
@Data
public class ArticleFormParams implements Serializable {

    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容", name = "content", required = true, example = "admin")
    private String content;

    /**
     * 图片ids
     */
    @ApiModelProperty(value = "图片ids", name = "imgIds", required = true, example = "1,2,3")
    private String imgIds;

    /**
     * 所属分类id
     */
    @ApiModelProperty(value = "所属分类id", name = "cateId", required = true, example = "1")
    private Integer cateId;

    /**
     * 标签ids
     */
    @ApiModelProperty(value = "标签ids", name = "tagIds", required = true, example = "1,2,3")
    private String tagIds;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "uid", required = true, example = "1")
    private Integer uid;

    /**
     * 可见度
     */
    @ApiModelProperty(value = "可见度", name = "visibility", required = true, example = "2")
    private Integer visibility;

    /**
     * 位置信息
     */
    @ApiModelProperty(value = "位置信息", name = "location", required = false, example = "ddd")
    private String location;

    /**
     * 发布时间（可以发表之前的文章）
     */
    @ApiModelProperty(value = "发布时间", name = "publishTime", required = true, example = "1,2,3")
    private Date publishTime;

}
