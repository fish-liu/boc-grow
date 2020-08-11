package com.grow.demo.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


/**
 * @author liuxw
 * @since 1.0
 */
@Data
@Builder
public class Article{

    private Integer id;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 图片ids
     */
    private String imgIds;

    /**
     * 所属分类id
     */
    private Integer cateId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 可见度
     */
    private Integer visibility;

    /**
     * 状态
     */
    private int status;

    /**
     * 位置信息
     */
    private String location;

    /**
     * 发布时间（可以发表之前的文章）
     */
    private Date publishTime;

    /**
     * 记录生产的时间
     */
    private Date createTime;

    /**
     * 记录的最新更新时间
     */
    private Date updateTime;

}
