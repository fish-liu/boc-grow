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
public class Tags {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 分组id
     */
    private int groupId;


    /**
     * 类型，（1=公共标签，2=私有标签）
     */
    private int type;

    /**
     * 用户id，私有分类时，该字段有值。
     */
    private Integer uid;

    /**
     * 状态 0=正常，1=禁用，2=删除
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
