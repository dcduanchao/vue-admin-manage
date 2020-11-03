package com.example.dc.query;

import lombok.Data;

/**
 * @ Author     ：duanchao
 * @ Date       ： 17:12 2020/10/30
 * @ Description：
 */

@Data
public class UserQueryCriter {


    @JpaQuery(type = JpaQuery.Type.INNER_LIKE)
    private  String  name;

    @JpaQuery(propName = "age")
    private  Integer age1;


    @JpaQuery(propName = "userStatus")
    private  Integer status;
}