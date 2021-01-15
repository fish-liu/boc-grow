package com.grow.demo.common.enums;

/**
 * 公共分类枚举类
 * @author liuxw
 * @since 1.0
 */
public enum PublicCategoryTypeEnum {

    LIFE(1,"生活"),
    WORK(2,"工作"),
    SPORT(3,"运动");

    PublicCategoryTypeEnum(int code,String name){
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

    public static PublicCategoryTypeEnum getEnum(int code) {

        for(PublicCategoryTypeEnum t : PublicCategoryTypeEnum.values()){
            if(t.getCode() == code){
                return t;
            }
        }
        throw new IllegalArgumentException("unknow type:" + code);
    }


}
