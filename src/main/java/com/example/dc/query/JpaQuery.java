package com.example.dc.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @uthor     ：duanchao
 * @ Date       ： 16:26 2020/10/30
 * @ Description：
 */


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JpaQuery {

    String propName() default "";

    Type type() default Type.EQUAL;


    enum Type {

        EQUAL
        , GREATER_THAN
        // Dong ZhaoYang 2017/8/7 小于等于
        , LESS_THAN
        // Dong ZhaoYang 2017/8/7 中模糊查询
        , INNER_LIKE
        // Dong ZhaoYang 2017/8/7 左模糊查询
        , LEFT_LIKE
        // Dong ZhaoYang 2017/8/7 右模糊查询
        , RIGHT_LIKE
        // Dong ZhaoYang 2017/8/7 小于
        , LESS_THAN_NQ
        // jie 2019/6/4 包含
        , IN
        // 不等于
        ,NOT_EQUAL
        // between
        ,BETWEEN
        // 不为空
        ,NOT_NULL
    }
}
