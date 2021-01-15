package com.grow.demo.common.enums;

/**
 * 公共分类枚举
 * @author liuxw
 * @since 1.0
 */
public enum TagsTypeEnum {

    //其他
    PUBLIC(1,"公共分类"),
    PRIVATE(2,"私有分类");

    TagsTypeEnum(int code, String name){
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

    public static TagsTypeEnum getEnum(int code) {

        for(TagsTypeEnum t : TagsTypeEnum.values()){
            if(t.getCode() == code){
                return t;
            }
        }
        throw new IllegalArgumentException("unknow type:" + code);
    }

}
