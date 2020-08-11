package com.grow.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * 意见表
 * @author liuxw
 * @since 1.0
 */
@Data
public class Suggestion {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 意见标题
     */
    private String title;

    /**
     * 意见内容
     */
    private String context;


    /**
     * 状态（0=正常，1=禁用，2=删除）
     */
    private int status;

    /**
     * 提交人
     */
    private String submitter;

    /**
     * 提交人邮箱
     */
    private String email;

    /**
     * 记录生产的时间
     */
    private Date createTime;

    /**
     * 记录的最新更新时间
     */
    private Date updateTime;



}
