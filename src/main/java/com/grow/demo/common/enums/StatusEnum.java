package com.grow.demo.common.enums;

/**
 * @author liuxw
 * @version 1.0
 */
public enum StatusEnum {

    NORMAL(0,"正常"),
    DISABLE(1,"禁用"),
    DELETE(2,"删除");

    StatusEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    private int code;

    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static StatusEnum getEnum(int code) {

        for(StatusEnum t : StatusEnum.values()){
            if(t.getCode() == code){
                return t;
            }
        }
        throw new IllegalArgumentException("unknow type:" + code);
    }
}
