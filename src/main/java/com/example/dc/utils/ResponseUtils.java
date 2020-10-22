package com.example.dc.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 响应工具类
 * @author liuyao
 * @date 2020-03-30
 */
public class ResponseUtils {

    /**
     * 成功状态码
     */
    private static final int SUCESS_CODE = 200;

    /**
     * 成功描述
     */
    private static final String SUCESS_MSG = "成功";

    /**
     * 失败状态码
     */
    private static final int FAIL_CODE = 500;

    /**
     * 失败描述
     */
    private static final String FAIL_MSG = "服务器内部异常";


    /**
     * 成功访问
     * @param obj
     * @return
     */
    public static ElAdminResultBeans success(Object obj){
        ElAdminResultBeans elAdminResultBeans = new ElAdminResultBeans();
        elAdminResultBeans.setCode(SUCESS_CODE);
        elAdminResultBeans.setMsg(SUCESS_MSG);
        elAdminResultBeans.setData(obj);
        return  elAdminResultBeans;
    }

    /**
     * 成功访问无响应数据
     * @return
     */
    public static ElAdminResultBeans success(){
        ElAdminResultBeans elAdminResultBeans = new ElAdminResultBeans();
        elAdminResultBeans.setCode(SUCESS_CODE);
        elAdminResultBeans.setMsg(SUCESS_MSG);
        return  elAdminResultBeans;
    }

    /**
     * 异常访问
     * @param msg
     * @return
     */
    public static ElAdminResultBeans error(String msg){
        ElAdminResultBeans elAdminResultBeans = new ElAdminResultBeans();
        elAdminResultBeans.setCode(FAIL_CODE);
        elAdminResultBeans.setMsg(FAIL_MSG);
        if (StringUtils.isNotBlank(msg)){
            elAdminResultBeans.setMsg(msg);
        }
        return  elAdminResultBeans;
    }
}
