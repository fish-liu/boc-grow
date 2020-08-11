package com.grow.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author liuxw
 * @since 1.0
 */
@Data
public class User {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 姓名，（真实名字）
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱 与 密码进行登录
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别 （1=男，2=女）
     */
    private int gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 头像
     */
    private String avatar;


    /**
     * 状态（0=正常，1=禁用，2=删除）
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
