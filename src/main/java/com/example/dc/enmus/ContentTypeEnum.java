package com.example.dc.enmus;

/**
 * @ Author     ：duanchao
 * @ Date       ： 10:13 2020/11/6
 * @ Description：
 */
public enum  ContentTypeEnum {


    IMAGE_JPEG("image/jpeg", "jpg"),

    IMAGE_PNG("image/png", "png"),

    OCTET_STREAM("application/octet-stream","application/octet-stream"),

    ;

    private String value;

    private String desc;


    ContentTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public static ContentTypeEnum getValueEnum(String value) {
        ContentTypeEnum[] enums = values();
        for (ContentTypeEnum typeEnum : enums) {
            if (typeEnum.getValue().equals(value)) {
                return typeEnum;
            }
        }
        return null;
    }


    public static ContentTypeEnum getDescEnum(String desc) {
        ContentTypeEnum[] enums = values();
        for (ContentTypeEnum typeEnum : enums) {
            if (typeEnum.getDesc().equals(desc)) {
                return typeEnum;
            }
        }
        return null;
    }


    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}