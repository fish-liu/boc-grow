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
public class Category {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 分类名
     */
    private String categoryName;

    /**
     * 用户id,私有分类时，该字段有值。
     */
    private Integer uid;

    /**
     * 类型，（0=默认分类（其他）,1=公共分类，2=私有分类）
     * 默认分类的意义：如果用户没有创建自定义分类，用其他来包含没有定义到的分类
     * 私有分类可以删除
     */
    private int type;

    /**
     * 状态，0=正常，1=禁用，2=删除
     */
    private int status;

    /**
     * 记录生产的时间
     */
    private Date createTime;

    /**
     * 记录的最新更新时间
     */
    private Date updateTime;
}
