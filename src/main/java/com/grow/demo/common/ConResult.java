package com.grow.demo.common;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 统一返回格式
 * @author liuxw
 */
@ApiModel(value = "返回说明",description="公共返回对象ReQueryResult")
public class ConResult<T> implements Serializable {

    public static final String DEFAULT_ERROR_MESSAGE = "系统操作过程中出现错误,请重试";

    @ApiModelProperty(value = "成功标识；true：成功；false:失败")
    private boolean success;

    @ApiModelProperty(value = "返回状态码；200:成功")
    private String code;

    @ApiModelProperty(value = "描述信息")
    private String message;

    private T data;


    public static ConResult success() {
        return success(null);
    }

    public static <T> ConResult success(T data) {
        return new ConResult(true, null, data);
    }

    public static <T> ConResult success(String message, T data) {
        return new ConResult(true,message,data);
    }

    public static ConResult fail() {
        return fail("操作失败请重试!");
    }

    public static ConResult fail(String message) {

        if (StringUtils.isEmpty(message) || message.indexOf("Exception") > 0) {
            message = DEFAULT_ERROR_MESSAGE;
        }
        return new ConResult(false,message,null);
    }


    public ConResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
