package com.example.dc.enmus;

/**
 * @ Author     ：duanchao
 * @ Date       ： 10:10 2020/11/6
 * @ Description：
 */
public enum MinioBucketTypeEnum {

    /**公有*/
    PUBLIC_BUCKET(0, "public-bucket"),
    /**私有*/
    PRIVATE_BUCKET(1, "private-bucket");

    private Integer value;

    private String desc;


    MinioBucketTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public static MinioBucketTypeEnum getValueEnum(Integer value) {
        MinioBucketTypeEnum[] enums = values();
        for (MinioBucketTypeEnum typeEnum : enums) {
            if (typeEnum.getValue().equals(value)) {
                return typeEnum;
            }
        }
        return null;
    }


    public static MinioBucketTypeEnum getDescEnum(String desc) {
        MinioBucketTypeEnum[] enums = values();
        for (MinioBucketTypeEnum typeEnum : enums) {
            if (typeEnum.getDesc().equals(desc)) {
                return typeEnum;
            }
        }
        return null;
    }


    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
