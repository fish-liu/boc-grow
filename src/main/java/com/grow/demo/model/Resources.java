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
public class Resources {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 文件名
     */
    private String name;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件类型（1=头像，2=图片，3=视频，4=文件）
     */
    private int type;


    /**
     * 文件大小
     */
    private float size;

    /**
     * 文件状态 （0=正常，1=禁用，2=删除）
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
