package com.grow.demo.common.enums;

/**
 * 标签分组枚举
 * @author liuxw
 * @since 1.0
 */
public enum  TagGroupEnum {

    DEFAULT(0,"默认分组"),
    //兴趣爱好
    INTEREST(1,"兴趣"),
    TRAVEL(2,"旅行"),
    FOOD(3,"美食"),
    LEARNING(4,"学习"),
    FAMILY(5,"家庭");

    TagGroupEnum(int code,String name){
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

    public static TagGroupEnum getEnum(int code) {

        for(TagGroupEnum t : TagGroupEnum.values()){
            if(t.getCode() == code){
                return t;
            }
        }
        throw new IllegalArgumentException("unknow type:" + code);
    }

}
