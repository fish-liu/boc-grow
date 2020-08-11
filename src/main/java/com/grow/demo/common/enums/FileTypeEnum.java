package com.grow.demo.common.enums;

/**
 * 文件类型的枚举类
 * @author liuxw
 * @since 1.0
 */
public enum FileTypeEnum {

    AVATAR(1,"头像"),
    IMAGE(2,"图片"),
    FILE(3,"文件"),
    VIDEO(4,"视频");

    FileTypeEnum(int code,String name){
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

    public static FileTypeEnum getEnum(int code) {

        for(FileTypeEnum t : FileTypeEnum.values()){
            if(t.getCode() == code){
                return t;
            }
        }
        throw new IllegalArgumentException("unknow type:" + code);
    }

}
