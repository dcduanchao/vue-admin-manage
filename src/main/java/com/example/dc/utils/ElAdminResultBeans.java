package com.example.dc.utils;

import lombok.Data;

/**
 * 响应类
 * @author liuyao
 * @date 2020-03-31
 */
@Data
public class ElAdminResultBeans {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 响应描述信息
     */
    private String msg;

    /**
     * 响应参数
     */
    private Object data;


}
