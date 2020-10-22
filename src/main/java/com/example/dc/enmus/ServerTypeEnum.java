package com.example.dc.enmus;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:49 2020/9/14
 * @ Description：
 */
public enum ServerTypeEnum {
    /***/
    HOUR_DELIVERY(1,"72小时发货"),

    WITHOUT_REASON(2,"7天无理由退货"),

    DELAYS_WILL_COMPENSATE(3,"延误必赔"),

    RETURN_FREIGHT(4,"退货补运费"),

    THE_PACKAGE_MAILED(5,"全国包邮");



    private Integer value;

    private String desc;


    ServerTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    public static ServerTypeEnum getValueEnum(Integer value) {
        ServerTypeEnum[] enums = values();
        for (ServerTypeEnum typeEnum : enums) {
            if (typeEnum.getValue().equals(value)) {
                return typeEnum;
            }
        }
        return null;
    }


    public static ServerTypeEnum getDescEnum(String desc) {
        ServerTypeEnum[] enums = values();
        for (ServerTypeEnum typeEnum : enums) {
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
