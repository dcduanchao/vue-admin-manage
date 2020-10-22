package com.example.dc.enmus;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:49 2020/9/14
 * @ Description：
 */
public enum DiscountTypeEnum {
    /***/
    ACTIVITY(1,"活动"),

    LIMITED_TIME_OFFERS(2,"限时优惠"),

    SALES_PROMOTION(3,"促销");


    private Integer value;

    private String desc;


    DiscountTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public static DiscountTypeEnum getValueEnum(Integer value) {
        DiscountTypeEnum[] enums = values();
        for (DiscountTypeEnum typeEnum : enums) {
            if (typeEnum.getValue().equals(value)) {
                return typeEnum;
            }
        }
        return null;
    }


    public static DiscountTypeEnum getDescEnum(String desc) {
        DiscountTypeEnum[] enums = values();
        for (DiscountTypeEnum typeEnum : enums) {
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
